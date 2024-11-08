<template>
    <div class="HelloView">
        <h1>Hello</h1>
        <h2>Welcome to our Application</h2>
        <p>Your SoPra31-Team</p>
    </div>

</template>

<script>
export default {
    name: 'HelloView',
    components: {
    }
}
</script>

<style scoped>
.HelloView{
    display:flex;
    flex-direction: column;
    font-family: Arial, Helvetica, sans-serif;
    align-items: center;
    margin: 150px 20px 20px 20px;
    padding: 20px;
    background-color: rgba(0, 0, 0, 0.19);
    border-radius: 16px;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}
</style>