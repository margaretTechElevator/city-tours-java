<template>
  <div id="iphone14Layout">
    <div id="topNavPhoneArea">
      <div id="pingoLogo">pingo</div>
      <div id="myAccountIcon"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Topper",
};
</script>

<style>
#iphone14Layout {
  width: 390px;
  height: 844px;
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
#myAccountIcon{

    border-radius: 26px;
border: 2px solid var(--mint, #CFE7CA);
background-image: url('https://www.google.com/url?sa=i&url=https%3A%2F%2F1000logos.net%2Fgoogle-photos-logo%2F&psig=AOvVaw3quy0yHPzzju-aRBhknStN&ust=1695788772673000&source=images&cd=vfe&opi=89978449&ved=0CA8QjRxqFwoTCJDgqc23x4EDFQAAAAAdAAAAABAE');

}
</style>