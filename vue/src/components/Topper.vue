<template>
  <div id="topper">
    <div id="topArea">
      <router-link to="/" id="logo">pingo</router-link>
      <img src="../assets/samantha.png" id="user-pic" />
    </div>
  </div>
</template>

<script>
export default {
  name: "Topper",
};
</script>

<style scoped>

#topper {
  position: fixed;
  top: 0;
  z-index: 100;
  width: 100%;
}
#topArea {
  border-radius: 0px 0px 20px 20px;
  background-image: linear-gradient(
    180deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );
  padding: 17px 26px;
  /* align-items: flex-start; */
  height: 100px;
  display: flex;
  position: relative;
}

#logo {
  color: var(--mint, #cfe7ca);
  text-shadow: 0px -4px 4px rgba(0, 0, 0, 0.2);
  font-family: Orelega One;
  font-size: 48px;
  text-align: center;
  text-decoration: none;
  padding-top: 80px;
  z-index: auto;
  position:absolute;
  left: 50%;
  width:100px;
  margin: -50px 0 0 -50px;



}

#user-pic {
  overflow: hidden;
  border-radius: 50%;
  width: 26px;
  height: 26px;
  object-fit: cover;
  border: 2px solid var(--mint, #cfe7ca);
  margin-top:65px;
  z-index: 3;
  position:absolute;
  right:26px;
 

}

</style>

