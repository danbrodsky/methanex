<template>
  <div class="content" >
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <div>
            <b-alert :show=SuccessBanner dismissible variant="success">
              <h4 class="alert-heading">Change successful</h4>
            </b-alert>
          </div>
          <card>
            <vue-good-table
              :columns="columns"
              :rows="rows"
              :paginate="true"
              :search-options="{ enabled: true, trigger: 'enter' }"
              :pagination-options="{enabled: true, perPage: 10}"
              styleClass="vgt-table striped bordered">
              <template slot="table-column" slot-scope="props">
                  {{props.column.label}}
              </template>
              <template slot="table-row" slot-scope="props">
                <span v-if="props.column.field === 'btn'">
                  <button v-if='hasAccess()' v-b-modal.editResourceModal  class="btn btn-warning btn-fill btn-sm" @click="populateEdit(props.row.originalIndex)">edit</button>
                  <button v-if='hasAccess()' class="btn btn-danger btn-fill btn-sm"  @click="removeResources(props.row.id)">delete</button>
                </span>
                <span v-else>
                  {{ props.formattedRow[props.column.field] }}
                </span>
              </template>
            </vue-good-table>
            <div v-if='hasAccess()'>
              <button v-b-modal.addResourceModal class="btn btn-info btn-fill float-right" >
                Create a resource
              </button>
              <b-modal
                id="addResourceModal"
                ref="addResource"
                @ok="addResource">
                <div >
                  <b-card bg-variant="light">
                    <b-form-group horizontal
                                  breakpoint="lg"
                                  label="Add Resource"
                                  label-size="md"
                                  label-class="font-weight-bold pt-0"
                                  class="mb-0">
                      <b-form-group horizontal
                                    label="Name:"
                                    label-class="text-sm-right"
                                    label-for="nestedName">
                        <b-form-input id="nestedName"
                                      v-model="addName"
                                      type="text"
                                      placeholder="Enter your name"></b-form-input>
                      </b-form-group>
                      <b-form-group horizontal
                                    label="Email:"
                                    type="email"
                                    label-class="text-sm-right"
                                    label-for="nestedEmail">
                        <b-form-input id="nestedEmail"
                                      v-model="addEmail"
                                      type="text"
                                      placeholder="Enter your email"></b-form-input>
                      </b-form-group>
                      <b-form-group horizontal
                                    label="Location:"
                                    label-class="text-sm-right"
                                    label-for="nestedLocation">
                        <b-form-input id="nestedLocation"
                                      v-model="addLocation"
                                      type="text"
                                      placeholder="Enter your location"></b-form-input>
                      </b-form-group>
                      <b-form-group horizontal
                                    label="Group:"
                                    label-class="text-sm-right"
                                    label-for="nestedLocation">
                      <multiselect v-model="selectedGroups"
                                   placeholder="Pick a group"
                                   label="name"
                                   track-by="group"
                                   :options="groups">
                      </multiselect>
                      <multiselect v-model="selectedManager"
                                   placeholder="Pick a manager"
                                   label="name"
                                   track-by="manager"
                                   :options="resources">
                      </multiselect>
                      <multiselect v-model="selectedPeerGroup"
                                   placeholder="Pick a peer group"
                                   label="name"
                                   track-by="peerGroup"
                                   :options="peerGroups">
                      </multiselect>
                      <multiselect v-model="selectedStatus"
                                   placeholder="Pick a status"
                                   label="name"
                                   track-by="status"
                                   :options="statuses">
                      </multiselect>
                      <pre class="language-json"></pre>
                      </b-form-group>
                    </b-form-group>
                  </b-card>
                </div>
              </b-modal>
              <b-modal
                id="editResourceModal"
                ref="editResource"
                @ok="editResource">
                <div >
                  <b-card bg-variant="light">
                    <b-form-group horizontal
                                  breakpoint="lg"
                                  label="Edit Resource"
                                  label-size="md"
                                  label-class="font-weight-bold pt-0"
                                  class="mb-0">
                      <b-form-group horizontal
                                    label="Name:"
                                    label-class="text-sm-right"
                                    label-for="nestedName">
                        <b-form-input id="nestedName"
                                      v-model="addName"
                                      type="text"
                                      placeholder="Enter your name"></b-form-input>
                      </b-form-group>
                      <b-form-group horizontal
                                    label="Email:"
                                    type="email"
                                    label-class="text-sm-right"
                                    label-for="nestedEmail">
                        <b-form-input id="nestedEmail"
                                      v-model="addEmail"
                                      type="text"
                                      placeholder="Enter your email"></b-form-input>
                      </b-form-group>
                      <b-form-group horizontal
                                    label="Location:"
                                    label-class="text-sm-right"
                                    label-for="nestedLocation">
                        <b-form-input id="nestedLocation"
                                      v-model="addLocation"
                                      type="text"
                                      placeholder="Enter your location"></b-form-input>
                      </b-form-group>
                      <multiselect v-model="addGroup"
                                   placeholder="Pick a group"
                                   :options="groups"
                                   label="name"
                                   :searchable="false">
                      </multiselect>
                      <multiselect v-model="addManager"
                                   placeholder="Pick a manager"
                                   :options="resources"
                                   label="name"
                                   :searchable="false">
                      </multiselect>
                      <multiselect v-model="addPeerGroup"
                                   placeholder="Pick a peer group"
                                   :options="peerGroups"
                                   label="name"
                                   :searchable="false">
                      </multiselect>
                      <multiselect v-model="addStatus"
                                   placeholder="Pick a status"
                                   :options="statuses"
                                   label="name"
                                   :searchable="false"
                                   ></multiselect>
                    </b-form-group>
                  </b-card>
                </div>
              </b-modal>
            </div>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import LTable from 'src/components/UIComponents/Table.vue'
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import axios from 'axios'
  import Multiselect from 'vue-multiselect'

  export default {
    components: {
      LTable,
      Card,
      Multiselect
    },
    created() {
      let that = this;
      axios.get(this.$root.serverURL + "/user/" + JSON.parse(that.$root.$data.cookies.get('user')).id + "/roles")
        .then(response => {
          that.role = response.data[0].name;
      });
      this.fetchData();
    },
    data() {
      return {
        SuccessBanner: false,
        addName: '',
        addEmail: '',
        addLocation: '',
        addGroup: null,
        addManager: null,
        addPeerGroup: null,
        addStatus: null,
        editId: -1,
        selectedRole: -1,
        allSelected: false,
        role: '',
        columns: [
          {
            label: 'Name',
            field: 'name',
            filterable: true,
          },
          {
            label: 'Email',
            field: 'email',
            type: 'string',
            filterable: true,
          },
          {
            label: 'Location',
            field: 'location',
            filterable: true,
          },
          {
            label: 'Group',
            field: 'group.name',
            filterable: true,
          },
          {
            label: 'Manager',
            field: 'manager.name',
            filterable: true
          },
          {
            label: '', // checkbox
            field: 'btn',
            sortable: false,
          }
        ],
        rows: [],
        selectedGroups: [],
        selectedManager: [],
        selectedPeerGroup: [],
        selectedStatus: [],
        groups: [],
        manager: [],
        resources: [],
        peerGroups: [],
        statuses: []
      };
    },
    methods: {
      fetchData() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/resources")
          .then(response => {
            info.rows = response.data;
            for (let i = 0; i < info.rows.length; i++) {
              if (info.rows[i].manager == null)
                info.rows[i].manager = {'name': ''};
              if (info.rows[i].group == null)
                info.rows[i].group = {'name': ''};
              if (info.rows[i].peerGroup == null)
                info.rows[i].peerGroup = {'name': ''};
              if (info.rows[i].status == null)
                info.rows[i].status = {'name': ''};
            }
          })
          .catch(error => console.log(error));
        axios
          .get(info.$root.serverURL + "/api/groups")
          .then(response => {
            info.groups = response.data;
          })
          .catch(error => console.log(error));
        axios
          .get(info.$root.serverURL + "/api/resources")
          .then(response => {
            info.resources = response.data;
          })
          .catch(error => console.log(error));
        axios
          .get(info.$root.serverURL + "/api/peergroups")
          .then(response => {
            info.peerGroups = response.data;
          })
          .catch(error => console.log(error));
        axios
          .get(info.$root.serverURL + "/api/resourcestatuses")
          .then(response => {
            info.statuses = response.data;
          })
          .catch(error => console.log(error));
      },
      addRow(id) {
        this.added.push(id);
      },
      removeResources(id) {
        let info = this;
        this.$dialog.confirm("Are you sure you want to delete this resource?", {
          loader: true
        })
          .then((dialog) => {
            axios.delete(this.$root.serverURL + "/api/resources/" + id)
              .then(() => {
                info.fetchData();
                dialog.close();
              })
              .catch(() => {
                console.log("error while adding resource");
                dialog.close();
              })
          })
          .catch(() => {
            console.log('Delete aborted');
          });
      },
      populateEdit(index) {
        this.addName = this.rows[index].name;
        this.addEmail = this.rows[index].email;
        this.addLocation = this.rows[index].location;
        this.addResource = this.rows[index].resource;
        this.addGroup = this.rows[index].group;
        this.addManager = this.rows[index].manager;
        this.addPeerGroup = this.rows[index].peerGroup;
        this.status = this.rows[index].status;
        this.editId = this.rows[index].id;
      },
      addResource() {
        let info = this;
        this.SuccessBanner = false;
        axios.post(this.$root.serverURL + "/api/resources", {
          name: info.addName,
          email: info.addEmail,
          location: info.addLocation,
          group: info.selectedGroups,
          manager: info.selectedManager,
          peerGroup: info.selectedPeerGroup,
          status: info.selectedStatus
        })
          .then(() => {
            info.SuccessBanner = true;
            info.fetchData();
          })
          .catch(() => console.log("error while adding resource"))
      },
      hasAccess() {
        return this.role == "ROLE_ADMIN";
      },
      editResource() {
        let info = this;
        this.SuccessBanner = false;
        axios.put(this.$root.serverURL + "/api/resources/" + this.editId, {
          name: info.addName,
          email: info.addEmail,
          location: info.addLocation,
          group: info.addGroup,
          manager: info.addManager,
          peerGroup: info.addPeerGroup,
          status: info.addStatus
        })
          .then(() => {
            info.SuccessBanner = true;
            info.fetchData();})
          .catch(() => console.log("error while adding resource"))
      },
      nameState() {
        return this.addName.length > 0;
      },
      emailState() {
        return this.addEmail.contains("methanex");
      },
      delete() {
        let info = this;
        let skillId = this.rowsNonTechnical[index.toString()].id;
        axios.delete(this.$root.serverURL + "/api/resources/" + resourceId)
          .then(() => {
            info.skillDeletedBanner = true;
            info.update();
          })
          .catch(() => console.log("error deleting nonTech skills"))
      }
    }
  }
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
