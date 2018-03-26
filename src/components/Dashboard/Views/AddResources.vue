<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card>
            <template slot="header">
              <div class="row">
                <div class="col-8">
                  <h4 class="card-title">Add reources</h4>
                  <p class="card-category">Select which resources to add to this project</p>
                </div>
                <div class="col-4">
                </div>
              </div>
            </template>
            <modal v-if="showModal" @close="showModal = false" v-bind:message="modalMessage" v-bind:success="requestSuccess"></modal>
            <date-modal v-if="showDateModal" v-bind:dateType="dateModalType" v-bind:index="selectedRowIndex"
                        @receiveDate="updateDateColumn" ></date-modal>
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
                    <input type="checkbox" v-model="rows[props.row.originalIndex].selected">
                  </label>
                </td>
              </template>
              <template slot="table-row" slot-scope="props">
                <td class="fancy">{{props.row.name}}</td>
                <td class="align-right" style="max-width: 50px">{{props.row.location}}</td>
                <td class="align-right">{{props.row.group}}</td>
                <td class="align-right">{{props.row.status}}</td>
                <td class="align-right" v-model="rows[props.row.originalIndex].startDate" @click="openDateInput('start', props.row.originalIndex)">
                  {{rows[props.row.originalIndex].startDate}}
                </td>
                <td class="align-right" v-model="rows[props.row.originalIndex].endDate" @click="openDateInput('end', props.row.originalIndex)">
                  {{rows[props.row.originalIndex].endDate}}
                </td>
              </template>
            </vue-good-table>
            <button type="submit" aria-hidden="true" v-on:click="addResources" style='margin-right:16px' class="btn btn-success btn-sm btn-fill float-right">Create</button>
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
          axios.get(this.$root.serverURL + "/api/resources")
            .then(response => {
              for (let i = 0; i < info.rows.length; i++){
                info.rows["startDate"] = '';
                info.rows["endDate"] = '';
              }
              info.rows = info.silenceWarnings(response.data);
              console.log(info.rows);
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

        openDateInput(type, rowIndex){
          console.log(type);
          console.log("rowIndex");
          console.log(rowIndex);
          this.selectedRowIndex = rowIndex;
          this.dateModalType = type;
          this.showDateModal = true;
        },

        addRow(id){
          this.added.push(id);
          console.log(id);
        },

        addResources(){
          var resourcesToAdd = [];
          var resourceIds = [];
          var projectId = this.$route.params.projectId;

          for(var i = 0; i<this.rows.length; i++){
            if(this.rows[i].selected){
              resourcesToAdd.push(this.rows[i]);
              resourceIds.push(this.rows[i].id);
            }
          }
          //whatever we dont care about efficiency
          if(resourcesToAdd.length > 0){
            for(var i=0; i<resourcesToAdd.length; i++){
              var info = this;
              axios.post("http://localhost:8080/api/projects/addResource?projectId=" + projectId, resourceIds)
                .then(function(res){
                  info.modalMessage = "Added resources successfully.";
                  info.requestSuccess = true;
                  info.showModal = true;
                }).catch(function (error){
                console.log("add to project error");
                console.log(error);
                info.modalMessage = "Failed to add resources."
                info.requestSuccess = false;
                info.showModal = true;
              });
              console.log("resource ID: " + resourcesToAdd[i].id);
              console.log("project ID: " + projectId);
              console.log("start date: " + resourcesToAdd[i].startDate);
              console.log("end date: " + resourcesToAdd[i].endDate);
              let url = "http://localhost:8080/api/resourceHistory?resourceId=" + resourcesToAdd[i].id + "&projectId=" + projectId + "&dur_start=" + resourcesToAdd[i].startDate + "&dur_end=" + resourcesToAdd[i].endDate;
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
        updateDateColumn: function(params) {
          console.log("updateDateColumn");
          console.log(params);
          this.showDateModal =false;
          var index = params[0];
          var date = params[1];
          var dateType = params[2];
          var row = this.rows[index];

          date = JSON.stringify(date).substring(1, 11);
          console.log(date);
          console.log(row);

          if(dateType == 'start'){
            row['startDate'] = date;
          }
          else {
            row['endDate'] = date;
          }
        }

      }
    }


</script>

<style scoped>

</style>
