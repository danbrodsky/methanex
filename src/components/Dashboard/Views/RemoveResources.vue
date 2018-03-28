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
                  <h4 class="card-title">Remove reources</h4>
                  <p class="card-category">Select which resources to remove from this project</p>
                </div>
                <div class="col-4">
                </div>
              </div>
            </template>
            <modal v-if="showModal" @close="showModal = false" v-bind:message="modalMessage" v-bind:success="requestSuccess"></modal>
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
              </template>
            </vue-good-table>
            <button type="submit" aria-hidden="true" v-on:click="removeResources" style='margin-right:16px' class="btn btn-success btn-sm btn-fill float-right">Remove resources</button>
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
    export default {
      name: "remove-resources",
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
              label: 'Remove Resource',
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
          axios.get(this.$root.serverURL + "/api/projects/" +projectId +"/resources",{})
            .then(response => {
              info.rows = response.data;
            })
            .catch(function(error){
              info.showModal = true;
              info.modalMessage = "Could not fetch resources from server.";
              info.requestSuccess = false;
            })
        },

        goBack(){
          console.log("navigate back one page");
          this.$router.go(-1);
        },

        removeResources(){
          var resourceIds = [];
          var projectId = this.$route.params.projectId;
          var remaining = [];

          for(var i = 0; i<this.rows.length; i++){
            if(this.rows[i].selected){
              resourceIds.push(this.rows[i].id);
            }else{
              remaining.push(this.rows[i]);
            }
          }

          if(resourceIds.length > 0){
            var info = this;
            console.log(resourceIds);
            axios.delete(this.$root.serverURL + "/api/projects/" + projectId +"/resources",
              {data: resourceIds}, {headers: {"Content-Type": "application/json"}})
              .then(response => {
                info.showModal = true;
                info.modalMessage = "Removed resources successfully.";
                info.requestSuccess = true;
                info.rows = remaining;
              })
              .catch(function(error){
                info.showModal = false;
                info.modalMessage = "Failed to remove resources.";
                info.requestSuccess = false;
              })
          }
        }


      }
    }

</script>

<style scoped>

</style>
