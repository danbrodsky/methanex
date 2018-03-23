<template>
    <div class="customtooltip">
        <svg ref="statusBar"
            width="100%"
            max-height="50px"
            viewBox="0 0 600 50"
            preserveAspectRatio="none">
                <rect ref="target1" x="0" y="0" width="100" height="25" style="fill: grey" />
                <rect ref="target2" x="100" y="0" width="100" height="25" style="fill: grey" />
                <rect ref="target3" x="200" y="0" width="100" height="25" style="fill: grey" />
                <rect ref="target4" x="300" y="0" width="100" height="25" style="fill: grey" />
                <rect ref="target5" x="400" y="0" width="100" height="25" style="fill: grey" />
                <rect ref="target6" x="500" y="0" width="100" height="25" style="fill: grey" />
                <rect ref="target7" x="600" y="0" width="100" height="25" style="fill: grey" />

                <rect ref="actual1" x="0" y="25" width="100" height="25" style="fill: green" />
                <rect ref="actual2" x="100" y="25" width="100" height="25" style="fill: green" />
                <rect ref="actual3" x="200" y="25" width="100" height="25" style="fill: green" />
                <rect ref="actual4" x="300" y="25" width="100" height="25" style="fill: green" />
                <rect ref="actual5" x="400" y="25" width="100" height="25" style="fill: green" />
                <rect ref="actual6" x="500" y="25" width="100" height="25" style="fill: green" />
                <rect ref="actual7" x="600" y="25" width="100" height="25" style="fill: green" />
        </svg>
        <span class="customtooltiptext">Some Text</span>
    </div>
</template>

<script>
export default {
    // props: {
    //     dates: {
    //         type: Object,
    //         default() { return {}; }
    //     }
    // },
    data() {
        dates: {}
    },
    mounted() {
        this.dates = {
            "exp_preapproval_date": "2018-03-19",
            "exp_seeking_funding_date": "2018-03-20",
            "exp_pipeline_date": "2018-03-25",
            "exp_to_confirm_date": "2018-03-28",
            "exp_closing_date": "2018-03-30",
            "exp_closed_date": "2018-03-31",

            "actual_preapproval_date": "2018-03-19",
            "actual_seeking_funding_date": "2018-03-20",
            "actual_pipeline_date": "2018-03-25",
            "actual_to_confirm_date": "2018-03-28",
            "actual_closing_date": "2018-03-30",
            "actual_closed_date": "2018-03-31",

            "current_status_percent": 30,
            "current_status": "Seeking Funding"
        };

        var target_segments = [
            this.$refs.target1,
            this.$refs.target2,
            this.$refs.target3,
            this.$refs.target4,
            this.$refs.target5,
            this.$refs.target6,
            this.$refs.target7
        ];
        var actual_segments = [
            this.$refs.actual1,
            this.$refs.actual2,
            this.$refs.actual3,
            this.$refs.actual4,
            this.$refs.actual5,
            this.$refs.actual6,
            this.$refs.actual7
        ];
        var target_dates = [
            new Date(this.dates.exp_preapproval_date),
            new Date(this.dates.exp_seeking_funding_date),
            new Date(this.dates.exp_pipeline_date),
            new Date(this.dates.exp_to_confirm_date),
            new Date(this.dates.exp_closing_date),
            new Date(this.dates.exp_closed_date)
        ];
        var actual_dates = [
            new Date(this.dates.actual_preapproval_date),
            new Date(this.dates.actual_seeking_funding_date),
            new Date(this.dates.actual_pipeline_date),
            new Date(this.dates.actual_to_confirm_date),
            new Date(this.dates.actual_closing_date),
            new Date(this.dates.actual_closed_date)
        ];

        var today = new Date();
        today.setHours = 0;
        today.setMinutes = 0;
        today.setSeconds = 0;
        today.setMilliseconds = 0;

        var fillColor = "#888888"; //filled
        var total = target_segments.length
        for(var i=0; i<total-1; i++) {
            if ((i==0 || target_dates[i-1] < today) && today <= target_dates[i]){
                
                var finishedPercent = Math.floor(100 * (today - target_dates[i-1])/(target_dates[i] - target_dates[i-1]));
                target_segments[i].style.fill = fillColor;
                target_segments[i].setAttribute("x", i*100);
                target_segments[i].setAttribute("width", finishedPercent);

                fillColor = "#FFFFFF"; //unfilled
                target_segments[total-1].style.fill = fillColor;
                target_segments[total-1].setAttribute("x", i*100 + finishedPercent);
                target_segments[total-1].setAttribute("width", 100 - finishedPercent);
            }
            else {
                target_segments[i].setAttribute("x", i*100);
                target_segments[i].style.fill = fillColor;
            }
        }

        fillColor = "green"; //filled
        total = actual_segments.length
        for(var i=0; i<total-1; i++) {
            if ((i==0 || actual_dates[i-1] < today) && today <= actual_dates[i]){
                
                var finishedPercent = Math.floor(100 * (today - actual_dates[i-1])/(actual_dates[i] - actual_dates[i-1]));
                actual_segments[i].style.fill = fillColor;
                actual_segments[i].setAttribute("x", i*100);
                actual_segments[i].setAttribute("width", finishedPercent);

                fillColor = "#FFFFFF"; //unfilled
                actual_segments[total-1].style.fill = fillColor;
                actual_segments[total-1].setAttribute("x", i*100 + finishedPercent);
                actual_segments[total-1].setAttribute("width", 100 - finishedPercent);
            }
            else {
                actual_segments[i].setAttribute("x", i*100);
                actual_segments[i].style.fill = fillColor;
            }
        }

    },
    data() {
        return {}
    }
}
</script>

<style scoped>

.customtooltip {
    position: relative;
    display: inline-block;
}

.customtooltip .customtooltiptext {
    visibility: hidden;
    width: 120px;
    background-color: black;
    color: #fff;
    text-align: center;
    border-radius: 6px;
    padding: 5px 0;
    position: absolute;
    z-index: 1;
    bottom: 150%;
    left: 50%;
    margin-left: -60px;
}

.customtooltip .customtooltiptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 50%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: black transparent transparent transparent;
}

.customtooltip:hover .customtooltiptext {
    visibility: visible;
}
</style>
