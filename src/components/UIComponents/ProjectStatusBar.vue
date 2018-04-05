<template>
    <div>
        <canvas ref="myCanvas" width="600" height="100"></canvas>
    </div>
</template>

<script>
export default {
    props: {
        "startDate": {
            type: Array,
            default: "2012-12-21"
        },
        "expectedPreApprovalStatusDate": {
            type: String,
            default: "2012-12-21"
        },
        "expectedSeekFundingStatusDate": {
            type: String,
            default: "2012-12-21"
        },
        "expectedPipelineStatusDate": {
            type: String,
            default: "2012-12-21"
        },
        "expectedConfirmedStatusDate": {
            type: String,
            default: "2012-12-21"
        },
        "expectedClosingStatusDate": {
            type: String,
            default: "2012-12-21"
        },
        "expectedClosedStatusDate": {
            type: String,
            default: "2012-12-21"
        },
        "actualPreApprovalStatusDate": {
            type: String,
            default: "2012-12-21"
        },
        "actualSeekFundingStatusDate": {
            type: String,
            default: "2012-12-21"
        },
        "actualPipelineStatusDate": {
            type: String,
            default: "2012-12-21"
        },
        "actualConfirmedStatusDate": {
            type: String,
            default: "2012-12-21"
        },
        "actualClosingStatusDate": {
            type: String,
            default: "2012-12-21"
        },
        "actualClosedStatusDate": {
            type: String,
            default: "2012-12-21"
        },
        "currentStatusPercent": {
            type: Number,
            default: 30
        },
        "currentStatus": {
            type: Object,
            default() { return {'id': 3, 'name': 'Pipeline'} }
        }
    },
    data() {
        dates: {}
    },
    mounted() {
        
        var target_dates = [
            new Date(this.startDate),
            new Date(this.expectedPreApprovalStatusDate),
            new Date(this.expectedSeekFundingStatusDate),
            new Date(this.expectedPipelineStatusDate),
            new Date(this.expectedConfirmedStatusDate),
            new Date(this.expectedClosingStatusDate),
            new Date(this.expectedClosedStatusDate)
        ];
        var actual_dates = [
            new Date(this.startDate),
            new Date(this.actualPreApprovalStatusDate),
            new Date(this.actualSeekFundingStatusDate),
            new Date(this.actualPipelineStatusDate),
            new Date(this.actualConfirmedStatusDate),
            new Date(this.actualClosingStatusDate),
            new Date(this.actualClosedStatusDate)
        ];

        var canvas = this.$refs.myCanvas;
        var ctx = canvas.getContext("2d");

        var today = new Date();
        today.setHours = 0;
        today.setMinutes = 0;
        today.setSeconds = 0;
        today.setMilliseconds = 0;

        var gradient = ctx.createLinearGradient(0,0,0,50);
        gradient.addColorStop(0, "#7386a3");
        gradient.addColorStop(0.4, "#4d6fa5");
        
        ctx.fillStyle = gradient;

        for(var i=0; i<target_dates.length-1; i++) {
            var tDate = target_dates[i+1]
            if(today < tDate) {
                var percent = Math.max(0, Math.floor(100 * (today - target_dates[i])/(target_dates[i+1] - target_dates[i])));
                ctx.fillRect(i*100, 0, percent,50);
            }
            else {
                ctx.fillRect(i*100,0,100,50);
            }
        }

        gradient = ctx.createLinearGradient(0,50,0,100);
        gradient.addColorStop(0.6, "#4286f4");
        gradient.addColorStop(1, "#70a1ef");

        ctx.fillStyle = gradient;

        for(var i=0; i<actual_dates.length-1; i++) {
            var aDate = actual_dates[i+1]
            if(today < aDate) {
                var percent = Math.max(0, Math.floor(100 * (today - actual_dates[i])/(actual_dates[i+1] - actual_dates[i])));
                ctx.fillRect(i*100, 50, percent,50);
            }
            else {
                ctx.fillRect(i*100,50,100,50);
            }
        }

    },
    data() {
        return {}
    }
}
</script>

<style scoped>
    canvas {
        width:          100%;
        height:         30px;
        border-radius:  1px;
        overflow:       hidden;
        border: solid black 1px;
        border-radius: 3px;
    }
</style>
