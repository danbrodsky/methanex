<template>
  <div class="content">
    <div>
      <b-alert :show=updatedProjectSuccessBanner dismissible variant="success">
        <h4 class="alert-heading">Project information has been updated</h4>
      </b-alert>
    </div>
    <div>
      <b-alert :show=addedResourcesBanner dismissible variant="success">
        <h4 class="alert-heading">Resource(s) added to this project</h4>
      </b-alert>
    </div>
    <div>
      <b-alert :show=deletedResourceBanner dismissible variant="success">
        <h4 class="alert-heading">Resource(s) removed from this project</h4>
      </b-alert>
    </div>
    <card>
      <h4 slot="header" class="card-title">Edit Project</h4>
      <form>
        <div class="row">
          <div class="col-md-3">
            <fg-input type="text"
                      label="Project ID"
                      :disabled="true"
                      placeholder="Project ID"
                      v-model="project.id">
            </fg-input>
          </div>
          <div class="col-md-5">
            <fg-input type="text"
                      label="Est. Remaining Cost"
                      placeholder="Estimated remaining cost"
                      v-model="project.estimatedRemainingCost">
            </fg-input>
          </div>
          <div class="col-md-5">
            <fg-input type="text"
                      label="Percentage Complete"
                      placeholder="% Complete"
                      v-model="project.percentageComplete">
            </fg-input>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <fg-input type="text"
                      label="Budget"
                      placeholder="Budget"
                      v-model="project.budget">
            </fg-input>
          </div>
          <div class="col-md-6">
            <fg-input label="Project Owner"
                      placeholder="Project Owner"
                      v-model="project.projectOwner.name">
            </fg-input>
          </div>
        </div>
        <div class="text-center">
          <div class="btn-toolbar float-right">
            <button type="submit" class="btn btn-info btn-fill float-right" @click.prevent="updateProject">
              Update Project
            </button>
          </div>
        </div>
        <div class="clearfix"></div>
      </form>
    </card>
    <card>
      <vue-good-table
        :columns="columns"
        :paginate="true"
        :rows="resources"
        :globalSearch="false"
        styleClass="table table-striped condensed">
        <template slot="table-column" slot-scope="props">
          <span v-if="props.column.field =='name'">
            <i class="fa fa-user"></i> {{props.column.label}}
          </span>
          <span v-else-if="props.column.field == 'joined'">
            <i class="fa fa-calendar"></i> {{props.column.label}}
          </span>
          <span v-else-if="props.column.label =='SelectAll'">
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
              <input type="checkbox" v-model="resources[props.row.originalIndex].selected">
            </label>
          </td>
        </template>
      </vue-good-table>
      <button type="submit" class="btn btn-info btn-fill float-right" style="margin-right: 5px;"
              @click.prevent="addResources">
        Add Resource
      </button>
      <button type="submit" class="btn btn-info btn-fill float-right" style="margin-right: 5px;"
              @click.prevent="removeResources">
        Remove Resource
      </button>
    </card>
  </div>
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import axios from 'axios'

  export default {
    components: {
      Card
    },
    data() {
      return {
        updatedProjectSuccessBanner: false,
        addedResourcesBanner: false,
        deletedResourceBanner: false,
        removed: false,
        columns: [
          {
            label: 'SelectAll',
            sortable: false,
          },
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
          }
        ],
        project: {
          id: 0,
          name: '',
          email: '',
          location: '',
          group: null,
          manager: null,
          status: null,
          projectOwner: null,
          estimatedRemainingCost: null,
          percentageComplete: null,
        },
        resources: []
      }
    },
    created() {
      this.fetchData();
    },
    methods: {
      toggleSelectAll() {
        this.allSelected = !this.allSelected;
        this.resources.forEach(row => {
          row.selected = this.allSelected;
        });
      },
      fetchData() {
        let info = this;
        axios
          .get(info.$root.serverURL + "/api/projects/" + this.$route.params.projectId)
          .then(response => {
            info.project = response.data;
            axios.get(info.$root.serverURL + "/api/projects/" + this.$route.params.projectId + "/resources")
              .then((response) => {
                info.resources = response.data;
                info.resources.forEach((resource) => {
                  resource["selected"] = false;
                  if (typeof resource.manager !== 'string' && resource.manager != null) {
                    resource.manager = resource.manager.name;
                  }
                });
              })
              .catch(() => console.log("error getting project resources"));
          })
          .catch(() => console.log("error fetching project"));
      },
      updateProject() {
        var info = this;
        axios.put(info.$root.serverURL + "/api/projects/" + info.project.id, info.project)
          .then(response => {
            info.updatedProjectSuccessBanner = true;
            info.project = response.data;
          })
          .catch(() => console.log("problem updating project"));
      },
      addResources() {
        var projectId = this.$route.params.projectId;
        let info = this;
        let data = [];
        this.resources.forEach(resource => {
          if (resource.selected) {
            data.push(resource.id);
          }
        });
        console.log(data);
        if (data.length > 0) {
          axios.post(this.$root.serverURL + "/api/projects/" + projectId + "/resources", data)
            .then((response) => {
              info.fetchData();
              info.addedResourcesBanner = true
            })
            .catch((error) => console.log(error));
        }
      },
      removeResources() {
        var projectId = this.$route.params.projectId;
        let info = this;
        let data = [];
        this.resources.forEach(resource => {
          if (resource.selected) {
            data.push(resource.id);
          }
        });
        if (data.length > 0) {
          let path = this.$root.serverURL + "/api/projects/" + projectId + "/resources?";
          data.forEach(resource => {
            path += "resourceId=" + resource + "&";
          });
          path = path.substring(0, path.length-1);
          axios.delete(path)
            .then((response) => {
              info.fetchData();
              info.deletedResourceBanner = true
            })
            .catch((error) => console.log(error));
        }
      }
    }
  }
</script>
<style>
</style>
