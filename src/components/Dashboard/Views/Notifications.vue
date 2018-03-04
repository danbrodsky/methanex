<template>
  <div class="content">
    <div class="container-fluid">
      <card>
        <template slot="header">
          <h4 class="card-title">Notifications</h4>
        </template>
        <div class="row">
          <div class="col-md-6">
            <h5>New</h5>
            <div class="alert alert-warning">
              <span>Minerva Hooper (1393234) wants to add the following skills: C++, Python</span>
              <button type="button" aria-hidden="true" style="position:absolute;right:8%;" class="btn btn-success btn-xs">&#10003;</button>
              <button type="button" aria-hidden="true" style="position:absolute;right:3%;"class="btn btn-danger btn-xs">&#10006;</button>
            </div>
            <div class="alert alert-warning">
              <span>George Cooper wants to be added to project 1</span>
              <button type="button" aria-hidden="true" style="position:absolute;right:8%;" class="btn btn-success btn-xs">&#10003;</button>
              <button type="button" aria-hidden="true" style="position:absolute;right:3%;"class="btn btn-danger btn-xs">&#10006;</button>
            </div>
            <div class="alert alert-warning">
              <span>Michael Brown has requested permission to edit project 2</span>
              <button type="button" aria-hidden="true" style="position:absolute;right:8%;" class="btn btn-success btn-xs">&#10003;</button>
              <button type="button" aria-hidden="true" style="position:absolute;right:3%;"class="btn btn-danger btn-xs">&#10006;</button>
            </div>
          </div>
          <div class="col-md-6">
            <h5>Recent</h5>
            <div class="alert alert-info">
              <button type="button" aria-hidden="true" class="close">×</button>
              <span><b> Info - </b> Some recent events</span>
            </div>
            <div class="alert alert-info">
              <button type="button" aria-hidden="true" class="close">×</button>
              <span><b> Info - </b> Some recent events</span>
            </div>
            <div class="alert alert-info">
              <button type="button" aria-hidden="true" class="close">×</button>
              <span><b> Info - </b> Some recent events</span>
            </div>
            <div class="alert alert-info">
              <button type="button" aria-hidden="true" class="close">×</button>
              <span><b> Info - </b> Some recent events</span>
            </div>
          </div>
        </div>
      </card>
    </div>
  </div>
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'

  export default {
    components: {
      Card
    },
    data () {
      return {
        type: ['', 'info', 'success', 'warning', 'danger'],
        notifications: {
          topCenter: false
        }
      }
    },
    methods: {
      notifyVue (verticalAlign, horizontalAlign) {
        const notification = {
          template: `<span>Welcome to <b>Light Bootstrap Dashboard</b> - a beautiful freebie for every web developer.</span>`
        }

        const color = Math.floor((Math.random() * 4) + 1)
        this.$notifications.notify(
          {
            component: notification,
            icon: 'nc-icon nc-app',
            horizontalAlign: horizontalAlign,
            verticalAlign: verticalAlign,
            type: this.type[color]
          })
      }
    }
  }

</script>
<style lang="scss">

</style>
