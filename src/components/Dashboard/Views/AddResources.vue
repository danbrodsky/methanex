<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card>
            <template slot="header">
              <div class="row">
                <button type="button" class="btn btn-primary btn-fill" style="margin-left: 16px" @click="goBack">Back</button>
              </div>
              <div class="row" style="padding-top: 25px">
                <div class="col-8">
                  <h4 class="card-title">Add reources</h4>
                  <p class="card-category">Select which resources to add to this project</p>
                </div>
                <div class="col-4">
                </div>
              </div>
            </template>
            <modal v-if="showModal" @close="showModal = false" v-bind:message="modalMessage" v-bind:success="requestSuccess"></modal>
            <date-modal v-if="showDateModal" v-bind:index="selectedRowIndex"
                        @receiveDate="updateDateColumns" @closeDateModal="cancelDateInput" ></date-modal>
            <vue-good-table
              :columns="columns"
              :paginate="true"
              :rows="rows"
              :globalSearch = "false"
              styleClass="table table-bordered table-striped">
              <template slot="table-column" slot-scope="props">
                <span v-if="props.column.label =='SelectAll'">
                  <label class="checkbox">
                    <input
                      type="checkbox"
                      @click="toggleSelectAll()">
                  </label>
                </span>
                <span v-else>
                    {{props.column.label}}
                </span>
              </template>
              <template slot="table-row-before" slot-scope="props">
                <td>
                  <label class="checkbox">
                    <input type="checkbox" v-model="rows[props.row.originalIndex].selected" @click="openDateInputFromCheckbox(props.row.originalIndex)">
                  </label>
                </td>
              </template>
              <template slot="table-row" slot-scope="props">
                <td class="fancy">{{props.row.name}}</td>
                <td class="align-right" style="max-width: 50px">{{props.row.location}}</td>
                <td class="align-right">{{props.row.group}}</td>
                <td class="align-right">{{props.row.status}}</td>
                <td class="align-right" v-model="rows[props.row.originalIndex].startDate" @click="openDateInputFromColumn(props.row.originalIndex)">
                  {{rows[props.row.originalIndex].startDate}}
                </td>
                <td class="align-right" v-model="rows[props.row.originalIndex].endDate" @click="openDateInputFromColumn(props.row.originalIndex)">
                  {{rows[props.row.originalIndex].endDate}}
                </td>
              </template>
            </vue-good-table>
            <button type="submit" aria-hidden="true" v-on:click="addResources" style='margin-top: 16px; margin-right:16px' class="btn btn-success btn-fill float-right">Add resource</button>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import Vue from 'vue';
  import axios from 'axios'
  import VueGoodTable from 'vue-good-table';
  import LTable from 'src/components/UIComponents/Table.vue'
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import Modal from 'src/components/UIComponents/ProjectComponents/Modal.vue'
  import DatePicker from 'vue2-datepicker'
  import DateModal from 'src/components/UIComponents/ProjectComponents/DateModal.vue'

  Vue.use(VueGoodTable);
    //<date-input-modal v-if="showModal" v-bind:index="selectedRowIndex" v-bind:show="showModal" @receiveDate="updateDateColumn" ></date-input-modal>
  //<input type="text" class="form-control" v-model="props.row.startDate">
  //
    export default {
      name: "add-resources",
      props: ['existingResources'],

      components: {
        LTable,
        Card,
        Modal,
        DateModal,
        DatePicker
      },

      name: 'Checkbox-table',
      created () {
        console.log("Existing resources:");
        console.log(this.existingResources);
        console.log("Existing resources end");
        this.fetchData();
        this.showModal = false;
      },

      data () {
        return {
          allSelected: false,
          showModal: false,
          startDateInput: "",
          endDateInput: "",
          showDateModal: false,
          selectedRowIndex: -1,

          columns: [
            {
              label: 'Add Resource',
              sortable: false,
            },
            {
              label: 'Name',
              type: 'text',
              field: 'name',
              filterable: true,
            },
            {
              label: 'Location',
              type: 'text',
              field: 'location',
              filterable: true,
            },
            {
              label: 'Group',
              type: 'text',
              field: 'group',
              filterable: true,
            },
            {
              label: 'Status',
              type: 'text',
              field: 'status',
              filterable: true,
            },
            {
              label: 'Start Date',
              field: 'startDate',
              filterable: false,
            },
            {
              label: 'End Date',
              field: 'endDate',
              filterable: false,
            }
          ],
          rows: [
            {selected: false, name:"John", age:20, joined: '20120201'},
            {selected: false, name:"Jane", age:24, joined: '20120305'},
            //...
          ],

          goToResource: function(row, index){
            console.log(row);
            // if(index > 0)
            //   this.$router.push({path: '/admin/user/' + row.id});
          }
        };
      },
      methods: {
        fetchData() {
          var info = this;
          var projectId = this.$route.params.projectId;
          axios.get(this.$root.serverURL + "/api/resources")
            .then(response => {

              // really should be doing this via the backend
              // calculating the set difference is not efficient on the client side
              axios.get(this.$root.serverURL + "/api/projects/" + projectId + "/resources")
                .then(response2 => {
                  var inProject = info.silenceWarnings(response2.data);
                  info.rows = info.silenceWarnings(response.data);
                  console.log("already in project");
                  console.log(inProject);
                  console.log("All resources");
                  console.log(info.rows);
                  console.log(inProject.length);
                  var diff = [];
                  if(inProject.length > 0 ){
                    for(var i=0; i<info.rows.length; i++){
                      var alreadyInProj = false;
                      for(var j=0; j<inProject.length && !alreadyInProj; j++){

                        if(inProject[j].id == info.rows[i].id){
                          alreadyInProj = true;
                          console.log("found someone thats already in the project");
                          console.log(inProject[j]);
                        }

                      }
                      if(!alreadyInProj){
                        diff.push(info.rows[i]);
                      }
                    }
                    info.rows = diff;
                    console.log("diff")
                    console.log(info.rows);
                  }

                  for (let i = 0; i < info.rows.length; i++){
                    info.rows["startDate"] = '';
                    info.rows["endDate"] = '';
                  }

                }).catch(function(error){
                  for (let i = 0; i < info.rows.length; i++){
                    info.rows["startDate"] = '';
                    info.rows["endDate"] = '';
                  }
                  // info.rows = info.silenceWarnings(response.data);
                  // console.log(info.rows);
              })


            })
        },

        silenceWarnings(resources) {
          for(let i = 0; i < resources.length; i++){
            if(typeof resources[i].group == 'undefined'){
              resources[i].group = '';
            }
            if(typeof resources[i].status == 'undefined'){
              resources[i].status = '';
            }
          }
          return resources;
        },

        openDateInputFromColumn(rowIndex){
          console.log("rowIndex");
          console.log(rowIndex);
          console.log("condition test");
          console.log(typeof(this.rows[rowIndex].startDate) != "undefined");
          if(typeof(this.rows[rowIndex].startDate) != "undefined" && typeof(this.rows[rowIndex].endDate )!= "undefined"
            && this.rows[rowIndex].startDate.length > 2 && this.rows[rowIndex].startDate.length > 2){
            console.log("Open date input start date:");
            console.log(typeof(this.rows[rowIndex].startDate) != "undefined");
            console.log(this.rows[rowIndex].startDate);
            console.log("Open date input end date:");
            console.log(this.rows[rowIndex].endDate);
            this.selectedRowIndex = rowIndex;
            this.showDateModal = true;
          }
        },

        openDateInputFromCheckbox(index){
          console.log(this.rows[index].selected);
          if(typeof(this.rows[index].selected) == "undefined" || !this.rows[index].selected){
            this.selectedRowIndex = index;
            this.showDateModal = true;
          }
          else {
            var row = this.rows[index];
            console.log("untick checkbox");
            row['startDate'] = "";
            row['endDate'] = "";
          }
        },

        cancelDateInput(){
          this.showDateModal = false;
          console.log(this.showDateModal);
          if(typeof(this.rows[this.selectedRowIndex].selected) != "undefined"
            && this.rows[this.selectedRowIndex].selected){
            this.rows[this.selectedRowIndex].selected = false;
          }
        },

        goBack(){
          console.log("navigate back one page");
          this.$router.go(-1);
        },

        addResources(){
          var resourcesToAdd = [];
          var resourceIds = [];
          var projectId = this.$route.params.projectId;
          var remaining = [];

          for(var i = 0; i<this.rows.length; i++){
            if(this.rows[i].selected){
              resourcesToAdd.push(this.rows[i]);
              resourceIds.push(this.rows[i].id);
            }else{
              remaining.push(this.rows[i]);
            }
          }

          //whatever we dont care about efficiency
          if(resourcesToAdd.length > 0){
            var info = this;
            console.log(resourcesToAdd);
            axios.post(this.$root.serverURL + "/api/projects/" + projectId + "/resources", resourceIds)
              .then(function(res){
                info.modalMessage = "Added resources successfully.";
                info.requestSuccess = true;
                info.showModal = true;
                info.rows = remaining;

              }).catch(function (error){
              console.log("add to project error");
              console.log(error);
              info.modalMessage = "Failed to add resources."
              info.requestSuccess = false;
              info.showModal = true;
            });

            for(var i=0; i<resourcesToAdd.length; i++){

              console.log("resource ID: " + resourcesToAdd[i].id);
              console.log("project ID: " + projectId);
              console.log("start date: " + resourcesToAdd[i].startDate);
              console.log("end date: " + resourcesToAdd[i].endDate);
              let url = this.$root.serverURL + "/api/resourceHistory?resourceId="
                + resourcesToAdd[i].id
                + "&projectId=" + projectId
                + "&dur_start=" + resourcesToAdd[i].startDate
                + "&dur_end=" + resourcesToAdd[i].endDate;
              axios.post(url)
                .then(function (res){
                  console.log(res);
                })
                .catch(function (error) {
                  console.log("add to resource history failure");
                  console.log(error);
                });
            }

          }
        },
        updateDateColumns: function(params) {
          console.log("updateDateColumn");
          console.log(params);
          this.showDateModal =false;

          var index = params[0];
          var startDate = params[1];
          var endDate = params[2];
          var row = this.rows[index];

          // console.log("startDate:");
          // console.log(startDate);
          // console.log("endDate:");
          // console.log(endDate);

          row['startDate'] = JSON.stringify(startDate).substring(1, 11);
          row['endDate'] = JSON.stringify(endDate).substring(1, 11);
        },

        showDatePlaceholder(index, dateType){
          if(dateType == "start"){
            if (typeof(this.rows[index].startDate) == "undefined" || this.rows[index].startDate == ''){
              return false;
            }
          }
           else{
            if (typeof(this.rows[index].endDate) == "undefined" || this.rows[index].endDate == ''){
              return false;
            }
          }
          return true;
        }
      }
    }


</script>

<style scoped>

</style>
