<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <div>
            <b-alert :show=SuccessBanner dismissible variant="success">
              <h4 class="alert-heading">Change sucessful</h4>
              <p>
                Please refresh the page to view any changes
              </p>
            </b-alert>
          </div>
          <card>
            <vue-good-table
              :columns="columns"
              :paginate="true"
              :rows="rows"
              :globalSearch="false"
              styleClass="table table-striped condensed">
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
              </template>
              <template slot="table-row-after" slot-scope="props">
                <td><button v-if='hasAccess()' v-b-modal.editResourceModal class="btn btn-warning btn-fill btn-sm" @click="populateEdit(props.row.originalIndex)">edit</button></td>
                <td><button v-if='hasAccess()' class="btn btn-danger btn-fill btn-sm" @click="removeResources(props.row.id)">delete</button></td>
              </template>
            </vue-good-table>
            <div v-if='hasAccess()'>
              <button v-b-modal.addResourceModal class="btn btn-success btn-fill float-right">
                Create a resource
              </button>
              <b-modal
                id="addResourceModal"
                ref="addResource"
                @ok="addResource">
                <div>
                  <b-card bg-variant="light">
                    <b-form-group horizontal
                                  breakpoint="lg"
                                  label="Resource"
                                  label-size="lg"
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
                                    label-for="nestedGroup">
                        <b-form-input id="nestedGroup"
                                      v-model="addGroup"
                                      type="text"
                                      placeholder="Enter your group"></b-form-input>
                      </b-form-group>
                      <b-form-group horizontal
                                    label="Role:"
                                    label-class="text-sm-right"
                                    class="mb-0">
                        <b-form-radio-group v-model="selectedRole" class="pt-2" :options="['User', 'Administrator']"/>
                      </b-form-group>
                    </b-form-group>
                  </b-card>
                </div>
              </b-modal>
              <b-modal
                id="editResourceModal"
                ref="editResource"
                @ok="editResource">
                <div>
                  <b-card bg-variant="light">
                    <b-form-group horizontal
                                  breakpoint="lg"
                                  label="Resource"
                                  label-size="lg"
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
                                    label-for="nestedGroup">
                        <b-form-input id="nestedGroup"
                                      v-model="addGroup"
                                      type="text"
                                      placeholder="Enter your group"></b-form-input>
                      </b-form-group>
                      <b-form-group horizontal
                                    label="Role:"
                                    label-class="text-sm-right"
                                    class="mb-0">
                        <b-form-radio-group v-model="selectedRole" class="pt-2" :options="['User', 'Administrator']"/>
                      </b-form-group>
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

  export default {
    components: {
      LTable,
      Card
    },
    created() {
      this.fetchData();
    },
    data() {
      return {
        SuccessBanner: false,
        addName: '',
        addEmail: '',
        addLocation: '',
        addGroup: null,
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
            field: 'group',
            filterable: true,
          },
          {
            label: 'Manager',
            field: 'manager',
            filterable: true
          },
          {
            label: ''
          },
          {
            label: ''
          }
        ],
        rows: [],
      };
    },
    methods: {
      fetchData() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/resources")
          .then(response => {
            info.rows = response.data;
            for (let i = 0; i < info.rows.length; i++) {
              if (info.rows[i].manager != null)
                info.rows[i].manager = info.rows[i].manager.name;
            }
          })
      },
      addRow(id) {
        this.added.push(id);
      },
      hasAccess() {
        return this.role == "ROLE_ADMIN";
      },
      removeResources(id) {
        let info = this;
        axios.delete(this.$root.serverURL + "/api/resources/" + id)
          .then(() => info.fetchData())
          .catch(() => console.log("error while adding resource"))
      },
      populateEdit(index) {
        this.addName = this.rows[index].name;
        this.addEmail = this.rows[index].email;
        this.addLocation = this.rows[index].location;
        this.addResource = this.rows[index].resource;
        this.addGroup = this.rows[index].group;
        this.editId = this.rows[index].id;
      },
      addResource() {
        let info = this;
        this.SuccessBanner = false;
        axios.post(this.$root.serverURL + "/api/resources", {
          name: info.addName,
          email: info.addEmail,
          location: info.addLocation,
          group: info.addGroup
        })
          .then(() => info.SuccessBanner = true)
          .catch(() => console.log("error while adding resource"))
      },
      editResource() {
        let info = this;
        this.SuccessBanner = false;
        axios.put(this.$root.serverURL + "/api/resources/" + this.editId, {
          name: info.addName,
          email: info.addEmail,
          location: info.addLocation,
          group: info.addGroup
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
<style>
</style>
