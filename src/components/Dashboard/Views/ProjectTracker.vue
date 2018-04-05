<template>
  <div class="content">
  <div class="header row" style="margin: 1%;">
    <pie-chart :chart-data="skillResourceCountDataCollection"></pie-chart>
    <bar-chart :chart-data="groupResourceCountDataCollection"></bar-chart>
    <gantt-chart v-bind:isPM="isPM" style="width: 80%;margin: auto;"></gantt-chart>
  </div>
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
      <h4 slot="header" class="card-title">Project</h4>
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
          <div class="col-md-3">
            <fg-input type="text"
                      label="Percentage Complete"
                      placeholder="% Complete"
                      v-model="project.percentageComplete">
            </fg-input>
          </div>
        </div>

        <div class="row">
          <div class="col-md-3">
            <fg-input type="text"
                      label="Budget"
                      placeholder="Budget"
                      v-model="project.budget">
            </fg-input>
          </div>
          <div v-if="!hasAccess()" class="col-md-6">
            <fg-input label="Project Owner"
                      placeholder="Project Owner"
                      v-model="project.projectOwnerName">
            </fg-input>
          </div>
          <div style="position:relative;left:5px;top:32px;" v-else>
            <multiselect v-model="project.projectOwner"
                         placeholder="Pick the business owner"
                         label="name"
                         track-by="name"
                         :options="allResources"
                         :multiple="false">
            </multiselect>
          </div>
        </div>
        <div class="text-center">
          <div class="btn-toolbar float-right" v-if="hasAccess()">
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
        :search-options="{ enabled: true, trigger: 'enter' }"
        :pagination-options="{enabled: true, perPage: 5}"
        :globalSearch="false"
        styleClass="table table-striped condensed">
        <template slot="table-column" slot-scope="props">
                <span v-if="props.column.label ==''">
                  <input @click="toggleSelectAll" type="checkbox"/>
                </span>

          <span v-else>
                    {{props.column.label}}
                </span>
        </template>
        <template slot="table-row" slot-scope="props">
            <span v-if="props.column.field === 'chkbx'">
              <input type="checkbox" v-model="resources[props.row.originalIndex].selected">
            </span>
          <span v-else>
            {{ props.formattedRow[props.column.field] }}
          </span>
        </template>
      </vue-good-table>
      <div v-if="hasAccess()" class="table-buttons">
        <button type="submit" class="btn btn-info btn-fill"
                @click.prevent="addResources">
          Add Resource
        </button>
        <button type="submit" class="btn btn-info btn-fill"
                @click.prevent="removeResources">
          Remove Resource
        </button>
      </div>
    </card>
  </div>
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import axios from 'axios'
  import GanttChart from 'src/components/UIComponents/PortfolioComponents/GanttChart.vue'
  import Multiselect from 'vue-multiselect'
  import PieChart from 'src/components/UIComponents/PieChart.js'
  import BarChart from 'src/components/UIComponents/BarChart.js'

  export default {
    components: {
      Card,
      GanttChart,
      Multiselect,
      PieChart,
      BarChart
    },
    mounted() {
      this.fillData();
    },
    data() {
      return {
        updatedProjectSuccessBanner: false,
        addedResourcesBanner: false,
        deletedResourceBanner: false,
        removed: false,
        role: '',
        isPM: false,
        allResources: [],
        columns: [
          {
            label: '', // checkbox
            field: 'chkbx',

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
          projectOwnerName: null,
          projectOwner: null,
          estimatedRemainingCost: null,
          percentageComplete: null,
        },
        resources: [],
        skillResourceCountDataCollection: null,
        groupResourceCountDataCollection: null
      }
    },
    created() {
      let that = this;
      axios.get(this.$root.serverURL + "/user/" + JSON.parse(that.$root.$data.cookies.get('user')).id + "/roles")
        .then(response => {
          that.role = response.data[0].name;
      });

      axios.get(this.$root.serverURL + "/api/resources")
        .then(response => {
          that.allResources = response.data;
      });
      this.fetchData();
    },
    methods: {
      fillData () {
        let info = this;
        let projectId = this.$route.params.projectId;
        console.log(projectId);
        axios
          .get(this.$root.serverURL + "/api/resourceSkillNumberData?projectId=" + projectId)
          .then(response => {
            console.log(response.data);
            let labels = [];
            let data = [];
            response.data.forEach(dataEntry => {
              labels.push(dataEntry.skill);
              data.push(dataEntry.number);
            });
            info.skillResourceCountDataCollection = {
              labels: labels,
              datasets: [
                {
                  label: '# Resources / Skill',
                  backgroundColor: '#5ebdff',
                  data: data
                }]
            }
          })
          .catch(error => console.log(error));

        axios
          .get(this.$root.serverURL + "/api/resourceGroupNumberData?projectId=" + projectId)
          .then(response => {
            console.log(response.data);
            let labels = [];
            let data = [];
            response.data.forEach(dataEntry => {
              labels.push(dataEntry.group);
              data.push(dataEntry.number);
            });
            info.groupResourceCountDataCollection = {
              labels: labels,
              datasets: [
                {
                  label: '# Resources / Group',
                  backgroundColor: '#68ff65',
                  data: data
                }]
            }
          })
          .catch(error => alert("problem loading pie chart" + error));
      },
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
            if (info.project.projectOwner != null) {
              info.project.projectOwnerName = info.project.projectOwner.name;
              // info.project.percentageComplete = info.project.
              if (JSON.parse(info.$root.$data.cookies.get('user')).resource.id == info.project.projectOwner.id){
                info.isPM = true;
              }
            }
            axios.get(info.$root.serverURL + "/api/projects/" + this.$route.params.projectId + "/resources")
              .then((response) => {
                info.resources = response.data;
                info.resources.forEach((resource) => {
                  resource["selected"] = false;
                  if (typeof resource.manager !== 'string' && resource.manager != null) {
                    resource.manager = resource.manager.name;
                  }
                  if (resource.group != null) {
                    resource.group = resource.group.name;
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
      hasAccess() {
        return this.role == "ROLE_ADMIN" || this.isPM;
      },
      addResources() {
        this.$router.push({path: `/admin/add-resources/${this.project.id}`});
      },
      goToResource(row, index) {
        this.$router.push({path: `/admin/user/${row.id}`});
      },
      removeResources() {
        let projectId = this.$route.params.projectId;
        let info = this;
        let data = [];
        this.resources.forEach(resource => {
          if (resource.selected) {
            data.push(resource.id);
          }
        });
        if (data.length > 0) {
          this.$dialog.confirm("Are you sure you want to deallocate this resource?", {
            loader: true
          })
            .then((dialog) => {
              let path = info.$root.serverURL + "/api/projects/" + projectId + "/resources?";
              data.forEach(resource => {
                path += "resourceIds=" + resource + "&";
              });
              path = path.substring(0, path.length-1);
              axios.delete(path)
                .then((response) => {
                  info.fetchData();
                  info.deletedResourceBanner = true;
                  dialog.close();
                })
                .catch((error) => {
                  console.log(error);
                  dialog.close();
                });
            })
            .catch(() => {
              console.log('Delete aborted');
            });
        }
      }
    }
  }
</script>
<style>
  .table-buttons {
    float: right;
  }
</style>
