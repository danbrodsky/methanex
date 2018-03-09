<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <img style="position:absolute;width:50%;top:5%;right:25%;z-index:-10;" src="https://d33wubrfki0l68.cloudfront.net/85975663ee3d10baa062c8406db6c5f10627a601/0a978/images/chart-title.png" alt="Mountain View">
            <label for="colFormLabelLg" style="position:absolute;top:2%;right:5%;font-size:30px;" class="col-form-label">ID: {{ project.id }} </label>
            <div class="form-group align-items-left">
              <label for="status" class="col-form-label">Project Name</label>
                  <input type="title" style="width:25%;" class="form-control form-control-lg" id="status" v-model="project.name">
              <label class="mt-2" for="status">Status</label>
              <select v-model="project.effort" class="form-control mr-sm-2" style="width:15%;" id="exampleFormControlSelect1">
                <option value="approved">Approved</option>
                <option value="pending">Pending</option>
                <option value="pipline">Pipeline</option>
                <option value="active">Active</option>
                <option value="complete">Complete</option>
              </select>
              <label for="colFormLabelLg" class="col-form-label">Business Owner</label>
                  <input type="title" style="width:15%;" class="form-control form-control-sm" v-model="project.manager">
              <label for="colFormLabelLg" class="col-form-label">Budget</label>
                  <input type="title" style="width:15%;" class="form-control form-control-sm" id="colFormLabelSm" v-model="project.budget">
              <label for="colFormLabelLg" class="col-form-label">Resource Breakdown</label>
                  <input type="" style="width:15%;" class="form-control form-control-sm" id="colFormLabelSm" value="Planning 8mm">
            </div>
            <div class="form-group" style="position:absolute;right:5%;top:80%;">
                <button type="button" v-if="isNewProject" v-on:click="addNewProject" style="display:block" class="btn">Add New Project</button>
                <button type="button" v-if="!isNewProject" v-on:click="updateProject" style="display:block;" class="btn btn-success">Update Project</button>
                <button type="button" class="mt-2 btn btn-danger">Delete Project</button>
            </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'

export default {
  created () {
    this.fetchData();
  },
  data () {
    return {
      isNewProject: false,
      project: {
        id: -1,
        name: "",
        manager: "",
        effort: "",
        startDate: new Date(),
        endDate: new Date(),
        budget: 0,
        ragStatus: "",
        portfolios:	[],
        resources:	[]
      }
    }
  },
  methods: {
    fetchData() {
      var info = this;
      if (this.$route.params.projectId === undefined) {
        this.isNewProject = true;
        return;
      }
      axios.get(this.$root.serverURL + "/api/projects/"+ this.$route.params.projectId)
      .then(response => {
        console.log(response.data);
        info.project = response.data;
      })
    },
    addNewProject() {
      axios.post(this.$root.serverURL + "/api/projects/", {
        "name": this.project.name,
        "budget": this.project.budget,
        "effort": this.project.effort
      })
      .then(function (res){
        console.log(res);
      });
    },
    updateProject() {
      axios.put(this.$root.serverURL + "/api/projects/" + this.project.id, {
        "name": this.project.name,
        "budget": this.project.budget,
        "effort": this.project.effort
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
