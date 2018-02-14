<template>
  <div class="card">
    <div class="card-header">
      <div name="header" style="align: center"><span style="font-weight:bold">{{ projectName }}</span> <span style="color:#888888">({{ projectId }})</span></div>
      <div name="header" style="font-weight:bold; color:#888888"></div>
    </div>
    <div class="card-body">
      <div class="body-data">
          Status: <i>{{ projectStatus }}</i>
      </div>
      <div class="body-data" style="width: 100%; padding:0">
          <progress style="width:100%" :value="projectProgress" max="100"></progress>
      </div>
      <div class="body-data">
          <span style="color:green;margin:0;display:inline-block;width:50%"><b>$</b> {{ budget }}</span>
          <span style="color:red;margin:0;display:inline-block"><b>$</b> {{ budgetUsed }}</span>
      </div>
      <div class="body-data">
          <span style="margin:0;display:inline-block;width:50%"><i class="fa fa-calendar"></i> {{ startDate }}</span>
          <span style="margin:0;display:inline-block"><i class="fa fa-calendar"></i> {{ endDate }}</span>
      </div>
      <div class="body-data">
          <span style="margin:0;display:inline-block;width:50%"><i class="fa fa-user"></i> {{ projectManager }}</span>
          <span style="margin:0;display:inline-block"><i class="fa fa-users"></i> {{ numPeopleOnTeam }}</span>
      </div>
    </div>
    <div class="card-footer" v-if="$slots.footer">
      <hr>
      <slot name="footer"></slot>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'project-card',
    props: {
        projectId: {
            type: Number,
            default: -1
        },
        projectName: {
            type: String,
            default: 'Project Name'
        },
        projectStatus: {
            type: String,
            default: 'The Status'
        },
        projectProgress: {
            type: Number,
            default: 8
        },
        projectManager: {
            type: String,
            default: 'Manager Name'
        },
        numPeopleOnTeam: {
            type: Number,
            default: 8
        },
        startDate: {
            type: String,
            default: 'MM/DD/YYYY'
        },
        endDate: {
            type: String,
            default: 'MM/DD/YYYY'
        },
        budget: {
            type: Number,
            default: 8
        },
        budgetUsed: {
            type: Number,
            default: 8
        }
    },
    data () {
        return {
            projectId: this.projectId,
            projectName: this.projectName,
            projectStatus: this.projectStatus,
            projectProgress: this.projectProgress,
            projectManager: this.projectManager,
            numPeopleOnTeam: this.numPeopleOnTeam,
            startDate: this.startDate,
            endDate: this.endDate,
            budget: this.budget,
            budgetUsed: this.budgetUsed
        }
    }
  }

</script>
<style scoped>
    div.card {
        min-width: 280px;
    }
    div.body-data {
        padding-top: 0.5em;
        padding-bottom: 0.5em;
    }
</style>
