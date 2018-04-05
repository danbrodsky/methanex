<template>
  <div class="card" style="cursor:pointer;background-color:#f9f7f7" v-on:click="goToProject">
    <div class="card-header">
      <div name="header" style="align: center; width: 75%"><span style="font-weight:bold">{{ project.name }}</span> <span style="color:#888888">({{ project.id }})</span>
      <span style="float:right;" v-if="project.status != null">
          Status: <i>{{ project.status.name }}</i>
      </span></div>
      <div name="header" style="font-weight:bold; color:#888888"></div>
    </div>
    <div class="card-body">
      <div class="body-data">
      </div>
      <div class="body-data" style="width: 100%; padding:0">
          <span style="position:relative;top:-5px;">Target: </span><span style="display:inline;"><project-status-bar style="position:relative;top:-20px;left: 27%;width:70%;"
            v-bind:startDate="project.startDate"
            v-bind:expectedPreApprovalStatusDate="project.expectedPreApprovalStatusDate"
            v-bind:expectedSeekFundingStatusDate="project.expectedSeekFundingStatusDate"
            v-bind:expectedPipelineStatusDate="project.expectedPipelineStatusDate"
            v-bind:expectedConfirmedStatusDate="project.expectedConfirmedStatusDate"
            v-bind:expectedClosingStatusDate="project.expectedClosingStatusDate"
            v-bind:expectedClosedStatusDate="project.expectedClosedStatusDate"
            v-bind:actualPreApprovalStatusDate="project.actualPreApprovalStatusDate"
            v-bind:actualSeekFundingStatusDate="project.actualSeekFundingStatusDate"
            v-bind:actualPipelineStatusDate="project.actualPipelineStatusDate"
            v-bind:actualConfirmedStatusDate="project.actualConfirmedStatusDate"
            v-bind:actualClosingStatusDate="project.actualClosingStatusDate"
            v-bind:actualClosedStatusDate="project.actualClosedStatusDate"
            v-bind:currentStatus="project.status">
          </project-status-bar></span>
      </div>
      <span style="position:relative;top:-40px;">Actual: </span>
      <div class="body-data" style="position:relative;top:-20px;left: 18%;width:70%;">
          <span style="margin:0;display:inline-block;width:50%;border:2px;"> <p>Budget:</p> <b>$</b>{{ project.budget }}</span>
          <span style="margin:0;display:inline-block;float:right;"> <p>Used:</p> <b>$</b>{{ project.budgetUsed }}</span>
      </div>
      <div class="body-data" style="margin:auto;text-align:center;">
          <span style="margin:auto;"><i class="fa fa-calendar"></i> &nbsp; {{ displayStartDate }}&nbsp;
          <i class="fa fa-long-arrow-right"></i>&nbsp; {{ displayEndDate }} </span>
      </div>
      <div class="body-data">
      <span v-if="project.manager != null">
        <span style="margin:0;top:17px;position:relative;"><i class="fa fa-user"></i> {{ project.manager.name }}</span>
                <span style="margin:0;display:inline-block;float:right;">
              <b-dropdown v-on:click="getResources" variant="btn-xs" no-caret id="project-card-dropdown">
                <template slot="button-content">
                <button style="color:black;border:none;" class="btn btn-sm" v-on:click="getResources">
                  <i style="color:black;" class="fa fa-users fa-lg"></i><span>
                  <i style="color:black;position:relative;left:10px;" class="fa fa-caret-down"></i></span><span class="sr-only"></span>
                  </button>
                </template>
              <b-dropdown-item v-for="resource of projectResources" v-bind:key="resource.id"> {{ resource.name }} </b-dropdown-item>
              </b-dropdown>
        </span>
      </span>
        </div>
    </div>
  </div>
</template>
<script>
  import ProjectStatusBar from 'src/components/UIComponents/ProjectStatusBar.vue'
  import axios from 'axios'
  export default {
    name: 'project-card',
    components: {
        ProjectStatusBar
    },
    props: {
        project: {
            type: Object,
            default() { return {} }
        }
    },
    created () {
            console.log(this.project);
          },
    data () {
        return {
          projectResources: []
        }
    },
    computed: {
        displayStartDate: function() {
            var startDate = new Date(this.project.startDate[0], this.project.startDate[1], this.project.startDate[2], 0, 0, 0);
            if (startDate == null) return "";
            return this.abbreviateMonth(startDate.getMonth()) + " " + startDate.getDate() + " " + startDate.getFullYear();
        },
        displayEndDate: function() {
            var endDate = new Date(this.project.endDate[0], this.project.endDate[1], this.project.endDate[2], 0, 0, 0);
            if (endDate == null) return "";
            return this.abbreviateMonth(endDate.getMonth()) + " " + endDate.getDate() + " " + endDate.getFullYear();
        },
    },
    methods: {
        goToProject() {
            this.$router.push({path: `/admin/project/${this.project.id}`});
        },
        getResources() {
          let that = this;
          axios.get(this.$root.serverURL + "/api/projects/" + this.project.id + "/resources")
            .then(response => {
              that.projectResources = response.data;
          })
        },
        abbreviateMonth (monthInteger) {
            switch(monthInteger) {
                case 0:
                    return "Jan";
                case 1:
                    return "Feb";
                case 2:
                    return "Mar";
                case 3:
                    return "Apr";
                case 4:
                    return "May";
                case 5:
                    return "Jun";
                case 6:
                    return "Jul";
                case 7:
                    return "Aug";
                case 8:
                    return "Sep";
                case 9:
                    return "Oct";
                case 10:
                    return "Nov";
                case 11:
                    return "Dec";
                default:
                    return "???";
            }
        }
    }
  }

</script>
<style scoped>
    div.card {
        width: 30%;
    }
    div.body-data {
        padding-top: 0.5em;
        padding-bottom: 0.5em;
    }
  #project-card-dropdown {
    border-radius: 2px !important;
  }

  .card-header {
    background-color: #f7f7f7;
  }
</style>
