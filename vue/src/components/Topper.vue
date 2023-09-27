<template>
  <div id="iphone14Layout">
    <div id="topNavPhoneArea">
      <div id="pingoLogo">pingo</div>
      <div id="myAccountIcon"><img src="../assets/samantha.png"/></div>
    </div>

  </div>
</template>

<script>
export default {
  name: "Topper",
};
</script>

<style scoped>
#iphone14Layout {
  width: 100%;

  margin: 0;
  /* font-family: Orelega One; */
}
#topNavPhoneArea {
  border-radius: 0px 0px 20px 20px;
  background-image: linear-gradient(
    180deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );

  display: flex;
  padding: 17px 26px;
  justify-content: flex-end;
  align-items: flex-end;
  /* flex: 1 0 0;
  align-self: stretch; */
}
#pingoLogo {
  color: var(--mint, #cfe7ca);
  text-shadow: 0px -4px 4px rgba(0, 0, 0, 0.2);
  font-family: Orelega One;
  font-size: 48px;
  font-style: normal;
  line-height: 64.336px;
  margin: auto;
  width: 50%;
  text-align: center;
}
#myAccountIcon {
  border-radius: 75%;
  height: 26px;
  width:26px;
  overflow: hidden;
  border: 2px solid var(--mint, #cfe7ca);

}
img{
  width: 26px;
  object-fit: cover;
}

</style>

<!-- /* small devices (landscape phones, 576 px and up) */
@media (min-width: 576px) {
  
}

/* medium devices (tablets, 768px and up) */
@media (min-width: 768px){

}
/* large devices (desktops 992px and up) */
@media(min-width: 992px){

}
/* extra large */
@media(min-width: 1200px){
  
} -->