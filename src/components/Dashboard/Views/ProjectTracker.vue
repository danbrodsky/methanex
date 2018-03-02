<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card>
          <!-- <img src="https://d33wubrfki0l68.cloudfront.net/85975663ee3d10baa062c8406db6c5f10627a601/0a978/images/chart-title.png" alt="Mountain View"> -->
            <label for="colFormLabelLg" class="col-form-label">ID: 54324 </label>
            <div class="row">
              <div class="form-group align-items-left" style="min-width:380px">
                <label for="status" class="col-form-label">Project Name</label>
                    <input type="title" style="width:25%;" class="form-control form-control-lg" id="status" v-bind:value="project.name">
                <label class="mt-2" for="status">Status</label>
                <select class="form-control mr-sm-2" style="width:15%;" id="exampleFormControlSelect1">
                  <option>Approved</option>
                  <option>Pending</option>
                  <option>Pipeline</option>
                  <option>Active</option>
                  <option>Complete</option>
                </select>
                <label for="colFormLabelLg" class="col-form-label">Business Owner</label>
                    <input type="title" style="width:15%;" class="form-control form-control-sm" value="Bob">
                <label for="colFormLabelLg" class="col-form-label">Classification</label>
                    <input type="title" style="width:15%;" class="form-control form-control-sm" id="colFormLabelSm" value="Infrastructure">
                <label for="colFormLabelLg" class="col-form-label">Resource Breakdown</label>
                    <input type="" style="width:15%;" class="form-control form-control-sm" id="colFormLabelSm" value="Planning 8mm">
              </div>
              <div class="form-group">
                <button type="button" v-if="isNewProject" v-on:click="addNewProject" style="display:block" class="btn">Add New Project</button>
                <button type="button" v-if="!isNewProject" style="display:block;" class="btn btn-success">Update Project</button>
                <button type="button" class="mt-2 btn btn-danger">Delete Project</button>
              </div>
            </div>
          </card>
        </div>
      </div>
      <div class="row">
        <filter-bar 
          style="width: 100%;margin: 0.5%;box-shadow: 5px 5px 5px grey"
          v-bind:sortingOptions="sortingOptions" 
          v-bind:filterOptions="filterOptions">
        </filter-bar>
      </div>
      <div class="row">
        <resource-card></resource-card>
        <resource-card></resource-card>
        <resource-card></resource-card>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
import ResourceCard from 'src/components/UIComponents/Cards/ResourceCard.vue'
import FilterBar from 'src/components/UIComponents/FilterBar.vue'

export default {
  components: {
    ResourceCard,
    FilterBar
  },
  created () {
    this.fetchData();
  },
  data () {
    return {
      isNewProject: false,
      project: {
        "id": -1,
        "name": "",
        "email": "",
        "location": "",
        "manager": null,
        "status": null
      },
      sortingOptions: [
          { value: 'id', text: 'ID' },
          { value: 'name', text: 'Name' },
          { value: 'status', text: 'Status' },
          { value: 'location', text: 'Location' }
        ],
        filterOptions: [
          { value: {category: 'id', type: Number}, text: 'ID' },
          { value: {category: 'name', type: String}, text: 'Name' },
          { value: {category: 'status', type: String}, text: 'status' },
          { value: {category: 'location', type: String}, text: 'Location' }
        ]
    }
  },
  methods: {
    fetchData() {
      var info = this;
      if (this.$route.params.projectId === undefined) {
        this.isNewProject = true;
        return;
      }
      axios.get(this.$root.serverURL + "/api/projs/"+ this.$route.params.projectId)
        .then(response => {
          console.log(response.data);
          info.projects = response.data;
        })
    },
    addNewProject() {
      axios.post(this.$root.serverURL + "/api/projects/", {
        "name": this.project.name,
        "email": this.project.email,
        "location": this.project.location,
        "manager": this.project.manager,
        "status": this.project.status
      })
      .then(function (res){
        console.log(res);
      });
    }
  }
}
  
</script>
<style>
</style>
