<template>
    <form  @submit.prevent="onSubmit">
      <div id="searchArea" class="grid">
        <input
          v-model="submitted_form.search_term"
          placeholder="starting address"
          type="input"
          class="input-text"
          id="startingAddress"
        />
        <input
          v-model="submitted_form.search_radius"
          placeholder="search radius"
          type="input"
          class="input-text"
          id="radius"
        />
        
        <span id="whatToSearch">things to do</span>
        <div id="attractionTypeCheckboxesGroup">
          <div
            v-for="type in attractionTypes" 
            :key="type"
            class="attractionTypeCheckboxes"
          >
            <input 
              type="checkbox" 
              :value="type" 
              v-model="submitted_form.selected_types"
              :id="`${type}-id`"
            />
            <label :for="`${type}-id`">{{ type }}</label>
          </div>

          <button type="submit" id="letsGo">let's go!</button>
        </div>
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
  
  ::placeholder {
    color: #e0a788e0;
    font-weight: 900;
    letter-spacing: 0.15rem;
    font-size: 0.75rem;
    background: transparent;
    font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  }

  #searchArea {
    grid-area: inputs;
    margin: auto;
    width: 50%;
    height: 150px;
    background-color: rgb(255, 255, 255);
    padding-bottom: 40px;
  }

  .input-text {
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
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-rows: 1fr 1fr 1fr 1fr 1fr;
    grid-template-areas: 
    "startingAddress startingAddress startingAddress startingAddress",
    "radius radius radius radius",
    ". whatToSearch whatToSearch .",
    "types types types types",
    ". letsGo letsGo .";
  }

  #startingAddress {
    grid-area: startingAddress;
  }

  #radius {
    grid-area: radius;
  }
  
  #whatToSearch {
    grid-area: whatToSearch;
    text-align: center;
    /* width: 100%; */
    padding-top: 20px;
    padding-bottom: 15px;
    color: #6b1717;
    font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
    font-weight: 900;
    font-size: 1.5rem;
    line-height: 0;
    text-shadow: 1px 1px 10px rgba(130, 114, 110, 0.5);
  }

  #attractionTypeCheckboxesGroup {
    grid-area: types;
  }
  
  .attractionTypeCheckboxes {
    display: inline-block;
    padding: 3%;
  }

  #letsGo {
    grid-area: letsGo;
  }
</style>