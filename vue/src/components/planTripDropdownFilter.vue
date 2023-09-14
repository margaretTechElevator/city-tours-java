<template>
  <div>
    <div id="filterTitle">plan your next adventure</div>

    <!-- starting address search area -->
    <div id="addressInput">
      <input
        v-model="currentInput"
        type="input"
        placeholder="where are you starting from?"
        id="startingAddressField"
      />
    </div>

    <!-- just here for some padding... work on removing paragraph -->
    <p></p>

    <!-- filter button -->
    <div id="filterButtonHolder">
      <button @click="showDropdown" id="filterButton">filter button... make filter dropdown go away when clicking outside</button>
      <div v-if="showMenu" class="menu">
        <!-- <div id="categoryButtons" v-for="type in attractionTypes" :key="type">
          <input type="checkbox" :value="type" v-model="selectedTypes" />
          {{ type }}
        </div> -->
        <div>search radius input or buttons</div>
        <div>checkbox of generated attraction 1</div>
        <div>checkbox of generated attraction 2</div>
        <div>checkbox of generated attraction 3</div>
        <div>checkbox of generated attraction 4</div>
        <div>checkbox of generated attraction 5</div>
      </div>
    </div>

    <div id="buttonHolder">
      <button>Go!</button>
    </div>


  </div>
</template>

<script>
export default {
  name: "TripDropdownFilter",
  data: function () {
    return {
      showMenu: false,
    };
  },
  props: {
    onClick: { type: Function },
  },
  methods: {
    showDropdown: function () {
      this.showMenu = !this.showMenu;
    },
  },
};
</script>

<style scoped>


#filterTitle {
  text-align: center;
  font-weight: 900;
  color: rgb(246, 89, 5);
  height: 30px;
  line-height: 2rem;
  font-size: 1.5rem;
}
/* #filterButton {
  background-color: yellow;
  text-align: center;
  width: 100px;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;


} */
#addressInput {
  text-align: center;
}
#startingAddressField {
  width:450px;
  text-align: center;
  margin-top:10px;
}
#filterButtonHolder {
  text-align: center;
  margin-bottom: 12px;
}

</style>