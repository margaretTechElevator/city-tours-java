<template>
  <div id="topNavPhoneArea">
    <div id="pingoLogo">pingo</div>
  </div>
</template>

<script>
export default {
  name: "Topper",
};
</script>

<style>
#topNavPhoneArea {
  border-radius: 0px 0px 20px 20px;
  background-image:  
  linear-gradient(
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
  gap: 222px;
  flex: 1 0 0;
  align-self: stretch;
}
#pingoLogo {
  color: var(--mint, #cfe7ca);
  text-align: center;
  text-shadow: 0px -4px 4px rgba(0, 0, 0, 0.2);
  font-family: Orelega One;
  font-size: 48px;
  font-style: normal;
  font-weight: 400;
  line-height: 134.033%; /* 64.336px */
}
</style>