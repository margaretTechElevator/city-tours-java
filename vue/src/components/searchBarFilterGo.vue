<template>
  <body>
    <div id="searchArea">
      <div id="pullBar"></div>

      <div id="searchBar">
        <form>
          <div id="startMag">

            <magnifyingGlass id="magnifyingGlass" />
            <input type="text" placeholder="starting from?" />

        </div>
        </form>
      </div>

      <div id="filterGo">
        <div id="filter">
          <FilterHamburger />
        </div>
        <div id="go">
          <button id="goButton">go!</button>
        </div>
      </div>
    </div>
  </body>
</template>

<script>
import FilterHamburger from "../assets/filterHamburger.vue";
import magnifyingGlass from "../assets/magnifyingGlass.vue";

export default {
  components: { FilterHamburger, magnifyingGlass },
  name: "SearchBarFilterGo",
};
</script>

<style scoped>
#searchBar {

  padding: 20px 26px;
  border: orange 4px solid;
}
#startMag {
  margin:auto;
  display: flex;
  width:440px;
  border: pink 3px solid;

}

#filterGo {
  display: flex;
}

#filter {
  flex: 8;
  padding-left: 26px;
}
#go {
  flex: 0;
  padding-right: 26px;
}

/* non-scoped to inherit from the component */
::placeholder {
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  font-family: Inter;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px;
  letter-spacing: 1.8px;
}


input {
  height: 30px;
  padding: 0px 16px;
  border-radius: 20px;
  background: rgba(25, 79, 119, 0.5);
  box-shadow: 0px 4px 3px 0px rgba(0, 0, 0, 0.6) inset;
  border: none;
  width:100%
}

#searchArea {
  overflow: hidden;
  z-index: 6;
  position: fixed;
  bottom: 0%;
  width: 100%;
  height: 162px;
  border-radius: 20px 20px 0px 0px;
  background: var(--dark-denim, #182935);
}
#pullBar {
  width: 50px;
  height: 3px;
  border-radius: 20px;
  background: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  z-index: 1;
  position: relative;
  top: 10px;
  margin: auto;
}

#goButton {
  display: flex;
  padding: 10px;
  justify-content: center;
  align-items: center;
  gap: 10px;
  border-radius: 20px;
  color: var(--selected-green, #adff00);
  text-align: right;
  font-family: Orelega One;
  font-size: 40px;
  font-style: normal;
  font-weight: 400;
  line-height: 12px;
  letter-spacing: 1px;
  text-transform: lowercase;
  background-color: #182935;
  border: none;
}
#magnifyingGlass{
  border:green 3px solid;
  position: relative;
  margin-right: 10px;
}

</style>