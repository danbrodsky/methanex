<template>
    <div id="addResourceRoot" class="card"　style="background-color: rgb(F5, F5, F5); width: 100%; margin: 0.5%; box-shadow: grey 5px 5px 5px;">
      <button type="button" v-if="isProjectManager" v-on:click="openCloseMenu" style="display:block" class="btn btn-block">Add Resource</button>
      <div id="addResourceMenu" v-if="isProjectManager && displayMenu">
        <label id="nameLabel" for="nameInput" class="col-form-label" style="">Name: </label>
        <input type="text" id="nameInput" class="form-control form-control-sm-4" v-model="name" />
        <label id="startDateLabel "for="startDateInput" class="col-form-label" v-model="startDate">Start date:</label>
        <input type="text" id="startDateInput" class="form-control" />
        <label id="endDateLabel"for="endDateInput" class="col-form-label">End date:</label>
        <input type="text" id="endDateInput" class="form-control form-control-sm-4" v-model="endDate"/>
        <div class="row">
          <div class="col">
            <button type="button" v-on:click="addNewResource" style="display:block" class="btn btn-primary">Ok</button>
          </div>
          <div class="col">
            <button type="button" v-on:click="closeMenu" style="display:block" class="btn btn-danger">Cancel</button>
          </div>
        </div>

      </div>
    </div>
</template>

<script>
    export default {
      name: "add-resource",
      props: ['isProjectManager'],

      data() {
        return {
          displayMenu: false,
          name: "",
          startDate: "",
          endDate: ""
        }
      },

      created(){
        console.log("isProjectManager?");
        console.log(this.isProjectManager);
      },

      methods: {
        openCloseMenu(){
          this.displayMenu = !this.displayMenu;
        },

        closeMenu(){
          this.displayMenu = false;
        },

        addNewResource() {
          axios.post(this.$root.serverURL + `/resourceHistory/`, {
          })
            .then(function (res){
              console.log(res);
              this.updatingStatus = "Saved!";
            });
        }
      }


    }


</script>

<style scoped>
  #nameLabel {

  }

</style>
