<template>
    <div>
        <svg ref="statusBar"
            width="100%"
            max-height="50px"
            viewBox="0 0 600 50"
            preserveAspectRatio="none">
                <rect x="0" y="0" width="100" height="25" style="fill: grey" />
                <rect x="100" y="0" width="100" height="25" style="fill: grey" />
                <rect x="200" y="0" width="100" height="25" style="fill: grey" />
                <rect x="300" y="0" width="100" height="25" style="fill: grey" />
                <rect x="400" y="0" width="100" height="25" style="fill: grey" />
                <rect x="500" y="0" width="100" height="25" style="fill: grey" />
                <rect x="0" y="25" width="100" height="25" style="fill: green" />
                <rect x="100" y="25" width="100" height="25" style="fill: green" />
                <rect x="200" y="25" width="100" height="25" style="fill: green" />
                <rect x="300" y="25" width="100" height="25" style="fill: green" />
                <rect x="400" y="25" width="100" height="25" style="fill: green" />
                <rect x="500" y="25" width="100" height="25" style="fill: green" />
        </svg>
    </div>
</template>

<script>
export default {
    props: {
        dates: {
            type: Object
        }
    },
    created() {
    },
    data() {
        return {}
    }
}
</script>

<style>

</style>
