<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card>
          <img style="position:absolute;width:50%;top:5%;right:25%;z-index:-10;" src="https://d33wubrfki0l68.cloudfront.net/85975663ee3d10baa062c8406db6c5f10627a601/0a978/images/chart-title.png" alt="Mountain View">
            <label for="colFormLabelLg" style="position:absolute;top:2%;right:5%;font-size:30px;"class="col-form-label">ID: 54324 </label>
            <div class="form-group align-items-left">
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
            <div class="form-group" style="position:absolute;right:5%;top:80%;">
              <button type="button" style="display:block;" class="btn btn-success">Update Project</button>
              <button type="button" class="mt-2 btn btn-danger">Delete Project</button>
            </div>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  created () {
    this.fetchData();
  },
  data () {
    return {
      project: {}
    }
  },
  methods: {
    fetchData() {
      var info = this;
      axios.get("https://peaceful-hamlet-75445.herokuapp.com/api/projects/"+ this.$router.params.projectId)
        .then(response => {
          console.log(response.data);
          info.projects = response.data;
        })
    }
  }
}
  
</script>
<style>
</style>
