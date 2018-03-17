<template>
  <div class="alert alert-info" style="line-height: 34px;">
    <div>
      <span> {{ resourceName }} wants to add the skill {{ skillName }} to their profile</span>
      <button type="submit" aria-hidden="true" v-on:click="dropRow()" style='margin-right:16px' class="btn btn-danger btn-sm btn-fill float-right">Decline</button>
      <button type="submit" aria-hidden="true" v-on:click="approve()" style='margin-right:16px' class="btn btn-success btn-sm btn-fill float-right">Approve</button>
    </div>
  </div>
</template>
<script>
  import axios from 'axios'

  export default {
    name: 'notification-row',
    props: {
        skillName: {
            type: String,
            default: 'Skill name'
        },
        resourceName: {
            type: String,
            default: 'Resource name'
        },
        resourceId: {
            type: Number,
            default: -1
        },
        managerId: {
            type: Number,
            default: -1
        },
        skillId: {
            type: Number,
            default: -1
        },
        notification: {
            type: Object,
            default: null
        },
        id: {
            type: Number,
            default: -1
        }
    },
    data () {
        return {
            // skillName: this.skillName,
            // resourceName: this.resourceName,
            // resourceId: this.resourceId,
            // managerId: this.managerId
        }
    },
    methods: {
      approve () {
        let info = this;
        console.log(info.notification);
        axios.post(info.$root.serverURL + "/api/notifications/" + info.resourceId + "/" + info.skillId).then(function (res){
          console.log(res);
        });
        info.dropRow()
      },
      dropRow () {
        let info = this;
        this.$emit('notification-remove', this.id);
        // axios.delete(info.$root.serverURL + "/api/notifications/" + info.managerId + "/" + info.resourceId + "/" + info.skillId)
        // .then(function (res){
        //   console.log(res);
        // });
      }
    }
  }

</script>
<style>
</style>
