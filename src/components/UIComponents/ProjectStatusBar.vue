<template>
    <div width="100%">
        <div class="customtooltip" v-show="!editMode">
            <svg ref="statusBar"
                width="100%"
                max-height="53px"
                viewBox="0 0 607 53"
                preserveAspectRatio="none">
                    <rect ref="target1" x="1" y="1" width="100" height="25" style="fill: grey" />
                    <rect ref="target2" x="102" y="1" width="100" height="25" style="fill: grey" />
                    <rect ref="target3" x="203" y="1" width="100" height="25" style="fill: grey" />
                    <rect ref="target4" x="304" y="1" width="100" height="25" style="fill: grey" />
                    <rect ref="target5" x="405" y="1" width="100" height="25" style="fill: grey" />
                    <rect ref="target6" x="506" y="1" width="100" height="25" style="fill: grey" />
                    <rect ref="target7" x="607" y="1" width="100" height="25" style="fill: grey" />

                    <rect ref="actual1" x="1" y="27" width="100" height="25" style="fill: green" />
                    <rect ref="actual2" x="102" y="27" width="100" height="25" style="fill: green" />
                    <rect ref="actual3" x="203" y="27" width="100" height="25" style="fill: green" />
                    <rect ref="actual4" x="304" y="27" width="100" height="25" style="fill: green" />
                    <rect ref="actual5" x="405" y="27" width="100" height="25" style="fill: green" />
                    <rect ref="actual6" x="506" y="27" width="100" height="25" style="fill: green" />
                    <rect ref="actual7" x="607" y="27" width="100" height="25" style="fill: green" />

                    <line x1="0" y1="0" x2="607" y2="0" style="stroke:black; stroke-width:1" />
                    <line x1="607" y1="0" x2="607" y2="53" style="stroke:black; stroke-width:1" />
                    <line x1="0" y1="0" x2="0" y2="53" style="stroke:black; stroke-width:1" />
                    <line x1="0" y1="53" x2="607" y2="53" style="stroke:black; stroke-width:1" />
                    
                    <line x1="0" y1="27" x2="607" y2="27" style="stroke:black; stroke-width:1" />

                    <line x1="101" y1="0" x2="101" y2="53" style="stroke:black; stroke-width:1" />
                    <line x1="202" y1="0" x2="202" y2="53" style="stroke:black; stroke-width:1" />
                    <line x1="303" y1="0" x2="303" y2="53" style="stroke:black; stroke-width:1" />
                    <line x1="404" y1="0" x2="404" y2="53" style="stroke:black; stroke-width:1" />
                    <line x1="505" y1="0" x2="505" y2="53" style="stroke:black; stroke-width:1" />
                    <line x1="606" y1="0" x2="606" y2="53" style="stroke:black; stroke-width:1" />
            </svg>
            <span v-if="showToolTip" class="customtooltiptext">
                Expected Status: {{ expectedCurrentStatus }} {{ expectedPercentage }}&#37;<hr style="border-color: inherit" />
                Actual Status: {{ datesData.current_status }} {{ datesData.current_status_percent }}&#37;
            </span>
            <div style="text-align:right; width:100%">
                <a href="javascript:void(0);" v-if="editable" v-on:click="editMode = true">Edit target value</a>
            </div>
        </div>
        <div v-if="editable" v-show="editMode">
            <div class="row">
                <div class="col-3">
                    <label>Target "Preapproval" Date: </label><br />
                    <date-picker lang="en" change="notifyParent" v-model="datesData.expectedPreApprovalStatusDate"></date-picker>
                </div>
                <div class="col-3">
                    <label>Target "Seeking Funding" Date: </label><br />
                    <date-picker lang="en" change="notifyParent" v-model="datesData.expectedSeekFundingStatusDate"></date-picker>
                </div>
                <div class="col-3">
                    <label>Target "Pipeline" Date: </label><br />
                    <date-picker lang="en" change="notifyParent" v-model="datesData.expectedPipelineStatusDate"></date-picker>
                </div>
                <div class="col-3">
                    <label>Target "To Confirm" Date: </label><br />
                    <date-picker lang="en" change="notifyParent" v-model="datesData.expectedConfirmedStatusDate"></date-picker>
                </div>
                <div class="col-3">
                    <label>Target "Closing" Date: </label><br />
                    <date-picker lang="en" change="notifyParent" v-model="datesData.expectedClosingStatusDate"></date-picker>
                </div>
                <div class="col-3">
                    <label>Target "Closed" Date: </label><br />
                    <date-picker lang="en" change="notifyParent" v-model="datesData.expectedClosedStatusDate"></date-picker>
                </div>
            </div>
            <div style="text-align:right; width:100%">
                <a href="javascript:void(0);" v-on:click="switchBackToBarView">Back</a>
            </div>
        </div>
    </div>
</template>

<script>
import DatePicker from 'vue2-datepicker'

export default {
    components: {
        DatePicker
    },
    props: {
        value: {
            type: Object,
            default() { return {}; }
        },
        editable: {
            type: Boolean,
            default: false
        },
        showToolTip: {
            type: Boolean,
            default: false
        }
    },
    created() {
        this.datesData = value;
    },
    mounted() {
        this.drawStatusBar();
    },
    data() {
        return {
            datesData: {},
            expectedCurrentStatus: "",
            expectedPercentage: 0,
            editMode: false
        }
    },
    methods: {
        switchBackToBarView(){
            this.drawStatusBar();
            this.editMode = false;
        },
        drawStatusBar(){
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
                new Date(this.datesData.expectedPreApprovalStatusDate),
                new Date(this.datesData.expectedSeekFundingStatusDate),
                new Date(this.datesData.expectedPipelineStatusDate),
                new Date(this.datesData.expectedConfirmedStatusDate),
                new Date(this.datesData.expectedClosingStatusDate),
                new Date(this.datesData.expectedClosedStatusDate)
            ];
            var actual_dates = [
                new Date(this.datesData.actual_preapproval_date),
                new Date(this.datesData.actual_seeking_funding_date),
                new Date(this.datesData.actual_pipeline_date),
                new Date(this.datesData.actual_to_confirm_date),
                new Date(this.datesData.actual_closing_date),
                new Date(this.datesData.actual_closed_date)
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
                    target_segments[i].setAttribute("x", i*101+1);
                    target_segments[i].setAttribute("width", finishedPercent);

                    fillColor = "#FFFFFF"; //unfilled
                    target_segments[total-1].style.fill = fillColor;
                    target_segments[total-1].setAttribute("x", i*101+1 + finishedPercent);
                    target_segments[total-1].setAttribute("width", 100 - finishedPercent);
                }
                else {
                    target_segments[i].setAttribute("x", i*101+1);
                    target_segments[i].style.fill = fillColor;
                }
            }

            fillColor = "green"; //filled
            total = actual_segments.length
            for(var i=0; i<total-1; i++) {
                if ((i==0 || actual_dates[i-1] < today) && today <= actual_dates[i]){
                    
                    var finishedPercent = Math.floor(100 * (today - actual_dates[i-1])/(actual_dates[i] - actual_dates[i-1]));
                    actual_segments[i].style.fill = fillColor;
                    actual_segments[i].setAttribute("x", i*101+1);
                    actual_segments[i].setAttribute("width", finishedPercent);

                    fillColor = "#FFFFFF"; //unfilled
                    actual_segments[total-1].style.fill = fillColor;
                    actual_segments[total-1].setAttribute("x", i*101+1 + finishedPercent);
                    actual_segments[total-1].setAttribute("width", 100 - finishedPercent);

                    this.expectedCurrentStatus = "Pipeline";
                    this.expectedPercentage = finishedPercent;
                }
                else {
                    actual_segments[i].setAttribute("x", i*101+1);
                    actual_segments[i].style.fill = fillColor;
                }
            }
        },
        notifyParent() {
            this.$emit('input', this.datesData);
        }
    }
}
</script>

<style scoped>

.customtooltip {
    position: relative;
    display: inline-block;
    width: 100%;
}

.customtooltip .customtooltiptext {
    visibility: hidden;
    background-color: black;
    color: #fff;
    text-align: center;
    border-radius: 6px;
    padding: 5px 0;
    position: absolute;
    z-index: 1;
    bottom: 150%;
    left: 10%;
    min-width:220px;
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
