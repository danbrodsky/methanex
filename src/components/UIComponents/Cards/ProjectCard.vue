<template>
  <div class="card">
    <div class="card-header">
      <div name="header" style="align: center; width: 75%"><span style="font-weight:bold">{{ projectName }}</span> <span style="color:#888888">({{ projectId }})</span></div>
      <div name="header" style="font-weight:bold; color:#888888"></div>
      <button class="btn" style="position:absolute;right:5%;top:5%;border-color:black;color:black;" v-on:click="goToProject"> Details </button>
    </div>
    <div class="card-body">
      <div class="body-data">
      <span v-if="projectStatus != null">
          Status: <i>{{ projectStatus.name }}</i>
      </span>
      </div>
      <div class="body-data" style="width: 100%; padding:0">
          <progress style="width:100%" :value="projectProgress" max="100"></progress>
          <project-status-bar></project-status-bar>
      </div>
      <div class="body-data">
          <span style="color:green;margin:0;display:inline-block;width:50%"><b>$</b> {{ budget }}</span>
          <span style="color:red;margin:0;display:inline-block"><b>$</b> {{ budgetUsed }}</span>
      </div>
      <div class="body-data">
          <span style="margin:0;display:inline-block"><i class="fa fa-calendar"></i> {{ displayStartDate }}</span>
          <span style="margin:0;display:inline-block"><i class="fa fa-calendar"></i> {{ displayEndDate }}</span>
      </div>
      <div class="body-data">
      <span v-if="projectManager != null">
        <span style="margin:0;display:inline-block;width:50%"><i class="fa fa-user"></i> {{ projectManager.name }}</span>
      </span>
        <!--<span style="margin:0;display:inline-block">-->
        <!--<drop-down tag="ul">-->
          <!--<template slot="title">-->
            <!--<span style="margin:0;display:inline-block"><i class="fa fa-users"></i> {{ numPeopleOnTeam }}</span>-->
            <!--<b class="caret"></b>-->
          <!--</template>-->
          <!--<a class="dropdown-item" href="#">Person 1</a>-->
          <!--<a class="dropdown-item" href="#">Person 2</a>-->
          <!--<a class="dropdown-item" href="#">Person 3</a>-->
          <!--<a class="dropdown-item" href="#">Person 4</a>-->
          <!--<a class="dropdown-item" href="#">Person 5</a>-->
        <!--</drop-down>-->
        <!--</span>-->
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
        projectId: {
            type: Number,
            default: -1
        },
        projectName: {
            type: String,
            default: 'Project Name'
        },
        projectStatus: {
            type: Object,
            default: 'The Status'
        },
        projectProgress: {
            type: Number,
            default: 8
        },
        projectManager: {
            type: Object,
            default: 'Manager Name'
        },
        numPeopleOnTeam: {
            type: Number,
            default: 8
        },
        startDate: {
            type: Date,
            default: function () { return new Date() }
        },
        endDate: {
            type: Date,
            default: function () { return new Date() }
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
            // projectId: this.projectId,
            // projectName: this.projectName,
            // projectStatus: this.projectStatus,
            // projectProgress: this.projectProgress,
            // projectManager: this.projectManager,
            // numPeopleOnTeam: this.numPeopleOnTeam,
            // startDate: this.startDate,
            // endDate: this.endDate,
            // budget: this.budget,
            // budgetUsed: this.budgetUsed
        }
    },
    computed: {
        displayStartDate: function() {
            return this.abbreviateMonth(this.startDate.getMonth()) + " " + this.startDate.getDate() + " " + this.startDate.getFullYear();
        },
        displayEndDate: function() {
            return this.abbreviateMonth(this.endDate.getMonth()) + " " + this.endDate.getDate() + " " + this.endDate.getFullYear();
        },
    },
    methods: {
        goToProject() {
            this.$router.push({path: `/admin/project/${this.projectId}`});
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
