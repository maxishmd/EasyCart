<template>
    <header>
        <div>
            <h1 id="directToHome" @click="showHome">EasyCart</h1>
        </div>
    </header>
</template>

<script>
export default {
    name: 'Header',
    components: {
    },
    methods: {
        showHome() {
            this.$emit('showComponent', 'HelloView');
        }
    }
}
</script>

<style scoped>
header {
    height: 40px;
    padding: 10px;
    background-color: rgba(40, 156, 171, 0.525);
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    font-size: 16px;
    font-family: Arial, sans-serif;
    font-weight: bold;
    font-variant: inherit;
    font-style: oblique;
    padding: 20px;
}

#directToHome:hover {
    cursor: pointer;
    background-color: rgba(40, 156, 171, 0.525);
    padding:3px;
    border-radius: 8px;
}
</style>