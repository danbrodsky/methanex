<template>
  <div class="card">
    <div class="card-header">
      <div name="header" style="align: center; width: 75%"><span style="font-weight:bold">{{ project.name }}</span> <span style="color:#888888">({{ project.id }})</span></div>
      <div name="header" style="font-weight:bold; color:#888888"></div>
      <button class="btn" style="position:absolute;right:5%;top:5%;border-color:black;color:black;" v-on:click="goToProject"> Details </button>
    </div>
    <div class="card-body">
      <div class="body-data">
      <span v-if="project.status != null">
          Status: <i>{{ project.status.name }}</i>
      </span>
      </div>
      <div class="body-data" style="width: 100%; padding:0">
          <project-status-bar
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
            v-bind:currentStatus="project.status"
            >
          </project-status-bar>
      </div>
      <div class="body-data">
          <span style="color:green;margin:0;display:inline-block;width:50%"><b>$</b> {{ project.budget }}</span>
          <span style="color:red;margin:0;display:inline-block"><b>$</b> {{ 8 }}</span>
      </div>
      <div class="body-data">
          <span style="margin:0;display:inline-block"><i class="fa fa-calendar"></i> {{ displayStartDate }}</span>
          <span style="margin:0;display:inline-block"><i class="fa fa-calendar"></i> {{ displayEndDate }}</span>
      </div>
      <div class="body-data">
      <span v-if="project.manager != null">
        <span style="margin:0;display:inline-block;width:50%"><i class="fa fa-user"></i> {{ project.manager.name }}</span>
                <span style="margin:0;display:inline-block">
        <drop-down tag="ul">
          <template slot="title">
            <span style="margin:0;display:inline-block"><i class="fa fa-users"></i> {{ numPeopleOnTeam }}</span>
            <b class="caret"></b>
          </template>
          <a v-for="portfolio of portfoliosDisplayed" href="#">Person 1</a>
          <a class="dropdown-item" href="#">Person 2</a>
          <a class="dropdown-item" href="#">Person 3</a>
          <a class="dropdown-item" href="#">Person 4</a>
          <a class="dropdown-item" href="#">Person 5</a>
        </drop-down>
</span>
      </span>
        </div>
    </div>
  </div>
</template>
<script>
  import ProjectStatusBar from 'src/components/UIComponents/ProjectStatusBar.vue'

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
    data () {
        return {}
    },
    computed: {
        displayStartDate: function() {
            var startDate = new Date(this.project.startDate);
            if (startDate == null) return "";
            return this.abbreviateMonth(startDate.getMonth()) + " " + startDate.getDate() + " " + startDate.getFullYear();
        },
        displayEndDate: function() {
            var endDate = new Date(this.project.endDate);
            if (endDate == null) return "";
            return this.abbreviateMonth(endDate.getMonth()) + " " + endDate.getDate() + " " + endDate.getFullYear();
        },
    },
    methods: {
        goToProject() {
            this.$router.push({path: `/admin/project/${this.project.id}`});
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
</style>
