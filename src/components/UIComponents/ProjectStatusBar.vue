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
            default() { return null }
        },
        "expectedPreapprovalStatusDate": {
            type: Array,
            default() { return null }
        },
        "expectedSeekingFundingStatusDate": {
            type: Array,
            default() { return null }
        },
        "expectedPipelineStatusDate": {
            type: Array,
            default() { return null }
        },
        "expectedConfirmStatusDate": {
            type: Array,
            default() { return null }
        },
        "expectedClosingStatusDate": {
            type: Array,
            default() { return null }
        },
        "expectedClosedStatusDate": {
            type: Array,
            default() { return null }
        },
        "actualPreapprovalStatusDate": {
            type: Array,
            default() { return null }
        },
        "actualSeekFundingStatusDate": {
            type: Array,
            default() { return null }
        },
        "actualPipelineStatusDate": {
            type: Array,
            default() { return null }
        },
        "actualConfirmedStatusDate": {
            type: Array,
            default() { return null }
        },
        "actualClosingStatusDate": {
            type: Array,
            default() { return null }
        },
        "actualClosedStatusDate": {
            type: Array,
            default() { return null }
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
        console.log("Working");
        console.log(this);

        // this.startDate = [2012, 1, 1];
        // this.expectedPreapprovalStatusDate = [2012, 1, 2];
        // this.expectedSeekingFundingStatusDate = [2018, 2, 2];
        // this.expectedPipelineStatusDate = [2018, 2, 3];
        // this.expectedConfirmStatusDate = [2018, 4, 20];
        // this.expectedClosingStatusDate = [2018, 4, 22];
        // this.expectedClosedStatusDate = [2018, 4, 25];
        // this.startDate = [2012, 1, 1];
        // this.actualPreapprovalStatusDate = [2012, 1, 2];
        // this.actualSeekFundingStatusDate = [2018, 2, 2];
        // this.actualPipelineStatusDate = [2018, 2, 3];
        // this.actualConfirmedStatusDate = [2018, 4, 20];
        // this.actualClosingStatusDate = null;
        // this.actualClosedStatusDate = null;
        

        var target_dates = [
            this.startDate == null ? null : new Date(this.startDate[0], this.startDate[1]-1, this.startDate[2], 0, 0, 0),
            this.expectedPreapprovalStatusDate == null ? null : new Date(this.expectedPreapprovalStatusDate[0], this.expectedPreapprovalStatusDate[1]-1, this.expectedPreapprovalStatusDate[2], 0, 0, 0),
            this.expectedSeekingFundingStatusDate == null ? null : new Date(this.expectedSeekingFundingStatusDate[0], this.expectedSeekingFundingStatusDate[1]-1, this.expectedSeekingFundingStatusDate[2], 0, 0, 0),
            this.expectedPipelineStatusDate == null ? null : new Date(this.expectedPipelineStatusDate[0], this.expectedPipelineStatusDate[1]-1, this.expectedPipelineStatusDate[2], 0, 0, 0),
            this.expectedConfirmStatusDate == null ? null : new Date(this.expectedConfirmStatusDate[0], this.expectedConfirmStatusDate[1]-1, this.expectedConfirmStatusDate[2], 0, 0, 0),
            this.expectedClosingStatusDate == null ? null : new Date(this.expectedClosingStatusDate[0], this.expectedClosingStatusDate[1]-1, this.expectedClosingStatusDate[2], 0, 0, 0),
            this.expectedClosedStatusDate == null ? null : new Date(this.expectedClosedStatusDate[0], this.expectedClosedStatusDate[1]-1, this.expectedClosedStatusDate[2], 0, 0, 0)
        ];
        var actual_dates = [
            this.startDate == null ? null : new Date(this.startDate[0], this.startDate[1]-1, this.startDate[2], 0, 0, 0),
            this.actualPreapprovalStatusDate == null ? null : new Date(this.actualPreapprovalStatusDate[0], this.actualPreapprovalStatusDate[1]-1, this.actualPreapprovalStatusDate[2], 0, 0, 0),
            this.actualSeekFundingStatusDate == null ? null : new Date(this.actualSeekFundingStatusDate[0], this.actualSeekFundingStatusDate[1]-1, this.actualSeekFundingStatusDate[2], 0, 0, 0),
            this.actualPipelineStatusDate == null ? null : new Date(this.actualPipelineStatusDate[0], this.actualPipelineStatusDate[1]-1, this.actualPipelineStatusDate[2], 0, 0, 0),
            this.actualConfirmedStatusDate == null ? null : new Date(this.actualConfirmedStatusDate[0], this.actualConfirmedStatusDate[1]-1, this.actualConfirmedStatusDate[2], 0, 0, 0),
            this.actualClosingStatusDate == null ? null : new Date(this.actualClosingStatusDate[0], this.actualClosingStatusDate[1]-1, this.actualClosingStatusDate[2], 0, 0, 0),
            this.actualClosedStatusDate == null ? null : new Date(this.actualClosedStatusDate[0], this.actualClosedStatusDate[1]-1, this.actualClosedStatusDate[2], 0, 0, 0)
        ];

        console.log("HERGH");
        console.log(target_dates);
        console.log(actual_dates);

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
            if(tDate == null) break;
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
            if(aDate == null) {
                ctx.fillRect(i*100, 50, this.currentStatusPercent,50);
                break;
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
