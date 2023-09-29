<template>
  <body id="accountArea">
    <div id="myTripsTitle">my trips</div>
    <trips-table />
    <div id="myToursTableBackground"></div>
  </body>
</template>
  
<script scoped>
import TripsTable from "../components/TripsTable.vue";

export default {
  components: { TripsTable },
  name: "MyToursPage",
};
</script>
  
<style scoped>
body {
  background: url("https://i.ibb.co/TKxNQrR/ny-Opacity.png");
}

#accountArea {
  width: 100%;
  height: 700px;
  border-radius: 0px 0px 20px 20px;
  background: linear-gradient(
    180deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );
}
#myTripsTitle {
  color: var(--selected-green, #adff00);
  text-align: center;
  font-family: Orelega One;
  font-size: 40px;
  font-style: normal;
  font-weight: 400;
  line-height: 50px; /* 125% */
  letter-spacing: 1px;
  text-transform: lowercase;
}
</style>