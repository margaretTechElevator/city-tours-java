<template>
    <form  @submit.prevent="onSubmit">
      <div id="searchArea" class="grid">
        <input
          v-model="submitted_form.search_term"
          placeholder="starting address"
          type="input"
          class="inputStartingMiles"
          id="startingAddress"
        />
        <input
          v-model="submitted_form.search_radius"
          placeholder="search radius in miles"
          type="input"
          class="inputStartingMiles"
          id="milesFrom"
        />
        <p></p>
        <span id="whatToSearch">things to do</span>
      
        <div v-for="type in attraction_types" :key="type">
          <input type="checkbox" :value="type" v-model="submitted_form.selected_types"/>
          {{ type }}
        </div>

        <button type="submit" id="letsGo">let's go!</button>
      </div>
    </form>
</template>

<script>
export default {
    name: "MapSearchForm",
    data() {
      return {
        submitted_form: {
            search_term: "beacon park",
            search_radius: "200",
            search_date: "",
            selected_types: [],
        },
        attraction_types: ["museum", "cafe", "restaurant", "park"],
        }
    },
    methods: {
        onSubmit() {
            this.$emit("search-submitted", this.submitted_form);
        }
    },
}
</script>

<style>
  input {
    width: 300px;
    margin-top: 0px;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    width: 50%;
    box-shadow: 1px 1px 10px rgba(255, 255, 255, 0.36);
    border:rgb(203, 203, 203) 0.5px solid;
    background-color: rgba(158, 158, 158, 0.248);
  }

  #searchArea {
    margin: auto;
    width: 50%;
    grid-area: inputs;
    background-color: rgb(255, 255, 255);
    padding-bottom: 40px;
  }

  .inputStartingMiles {
    width: 300px;
    margin-top: 10px;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    width: 100%;
    box-shadow: 1px 1px 10px rgba(255, 255, 255, 0.36);
    border:rgb(203, 203, 203) 0.5px solid;
    background-color: rgba(158, 158, 158, 0.248);
  }
      
  .grid {
    display: grid;
    grid-template-columns: 500px 100px 100px 100px;
    grid-template-rows: 1fr 1fr 1fr 1fr 1fr;
    grid-template-areas: 
    "startingAddress startingAddress startingAddress startingAddress",
    "milesFrom milesFrom milesFrom milesFrom",
    ". whatToSearch whatToSearch .",
    "radio radio radio radio",
    ". letsGo letsGo .";
  }

  #startingAddress {
    grid-area: startingAddress;
  }

  #milesFrom {
    grid-area: milesFrom;
  }
  
  #whatToSearch {
    grid-area: whatToSearch;
    text-align: center;
    padding-top: 20px;
    padding-bottom: 15px;
    color: #6b1717;
    font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
    font-weight: 900;
    font-size: 1.5rem;
    line-height: 0;
    text-shadow: 1px 1px 10px rgba(130, 114, 110, 0.5);
  }

  #letsGo {
    grid-area: letsGo;
  }
</style>