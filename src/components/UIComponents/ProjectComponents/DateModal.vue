<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="modal-header">
            <slot name="header">
              Enter start and end date:
            </slot>
          </div>

          <div class="modal-body">
            <slot name="body">
              <div>Start date:</div>
              <date-picker v-model="startDate" editable lang="en"></date-picker>
              <div>End date:</div>
              <date-picker v-model="endDate" editable lang="en"></date-picker>
              <div v-if="showWarning" style="color:orangered">{{warningMessage}}</div>
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              <div class="row">
                <div class="col">
                  <button class="btn btn-success btn-fill" @click="sendDates">
                    OK
                  </button>
                </div>
                <div class="col">
                  <button class="btn btn-danger btn-fill" @click="$emit('closeDateModal')">
                    Cancel
                  </button>
                </div>
              </div>

            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
  //
  import DatePicker from 'vue2-datepicker'

  export default {
    name: "date-modal",
    props: ['index'],

    components: {
      DatePicker
    },

    data(){
      return {
        startDate: "",
        endDate: "",
        showWarning: false,
        warningMessage: ""
      };
    },

    created(){
      console.log("modal create");
    },

    methods: {
      sendDates() {
        if(typeof(this.startDate) == "undefined"){
          this.warningMessage = "Please enter a start date.";
          this.showWarning = true;
        }
        else if(typeof(this.endDate) == "undefined"){
          this.showWarning  = true;
          this.warningMessage = "Please enter a end date.";
        }
        else{
          if(this.validateDates()){
            this.showWarning = false;
            this.$emit('receiveDate', [this.index, this.startDate, this.endDate]);
          }
        }
        //var start = new Date(this.startDate);
        //var start = new Date(this.endDate);
        //console.log(JSON.stringify(this.date));

      },

      validateDates(){
        this.showWarning = false;
        if(typeof(this.startDate) != "undefined" && typeof(this.endDate) != "undefined"){
          var start = new Date(this.startDate);
          var end = new Date(this.endDate);

          if(start > end){
            this.warningMessage = "Start date must be before end date.";
            this.showWarning =  true;
            return false;
          }
          return true;
        }
      }
    }
  }
</script>

<style scoped>
  .modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, .5);
    display: table;
    transition: opacity .3s ease;
  }

  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }

  .modal-container {
    width: 300px;
    margin: 0px auto;
    padding: 20px 30px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }

  .modal-header h3 {
    margin-top: 0;
    color: #42b983;
  }

  .modal-body {
    margin: 20px 0;
  }

  .modal-default-button {
    float: right;
  }

  /*
   * The following styles are auto-applied to elements with
   * transition="modal" when their visibility is toggled
   * by Vue.js.
   *
   * You can easily play with the modal transition by editing
   * these styles.
   */

  .modal-enter {
    opacity: 0;
  }

  .modal-leave-active {
    opacity: 0;
  }

  .modal-enter .modal-container,
  .modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }
</style>
