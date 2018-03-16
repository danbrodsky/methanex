<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-6" id="addProjectForm">
          <!-- <img src="https://d33wubrfki0l68.cloudfront.net/85975663ee3d10baa062c8406db6c5f10627a601/0a978/images/chart-title.png" alt="Mountain View"> -->
          <label for="addProjectForm" class="col-form-label">ID: {{project.id}} </label>
          <div class="form-group align-items-left" style="min-width:380px">
            <div class="col-xs-6">
              <label for="status" class="col-form-label">Project Name</label>
              <textarea type="title" class="form-control form-control-lg" id="status"
                        v-model="project.name" v-bind:disabled="!project.isProjectManager && !editMode"></textarea>
            </div>
            <label class="mt-2" for="status">Status</label>
            <select class="form-control mr-sm-2" style="width:20%;" id="exampleFormControlSelect1"
                    v-bind:disabled="!project.isProjectManager && !editMode" v-model="project.status">
              <option>Approved</option>
              <option>Pending</option>
              <option>Pipeline</option>
              <option>Active</option>
              <option>Complete</option>
            </select>
            <div class="col-xs-6">
              <label for="addProjectForm" class="col-form-label">Business Owner</label>
              <input type="title" style="width:25%;" class="form-control form-control-sm-4"
                     v-bind:value="project.businessOwner" v-bind:disabled="!project.isProjectManager && !editMode">
              <label for="addProjectForm" class="col-form-label">Classification</label>
              <input type="title" style="width:25%;" class="form-control form-control-sm-4"
                     id="classificationInput" v-bind:value="project.classification" v-bind:disabled="!project.isProjectManager && !editMode">
            </div>
          </div>
        </div>
        <div class="col-2">
          <div class="form-group" style="width: 100px">
            <button type="button" v-if="isNewProject" v-on:click="addNewProject" style="display:block" class="btn btn-block">Add New Project</button>
            <button type="button" v-if="!isNewProject && isProjectManager && !editMode" v-on:click="enableEdit" style="display:block" class="btn btn-success btn-block">Edit</button>
            <button type="button" v-else-if="!isNewProject && isProjectManager" style="display:block;" class="btn btn-success btn-block" v-on:click="updateProject">Update</button>
            <button type="button" class="mt-2 btn btn-danger btn-block" v-if="!isNewProject && isProjectManager" >Delete</button>
          </div>
        </div>
        <div class ="col-4" style="height:50%">
          <resource-breakdown v-bind:resourceData="resourceData"></resource-breakdown>
        </div>
      </div>
      <div class="row">
      </div>
      <div class="row">
        <filter-bar
          style="width: 100%;margin: 0.5%;box-shadow: 5px 5px 5px grey"
          v-model="filterFcn"
          v-bind:sortingOptions="sortingOptions"
          v-bind:filterOptions="filterOptions"
          v-on:newSearch="performSearch">
        </filter-bar>
      </div>

    </div>
  </div>
</template>

<script>
    export default {
        name: "create-project"
    }
</script>

<style scoped>

</style>
