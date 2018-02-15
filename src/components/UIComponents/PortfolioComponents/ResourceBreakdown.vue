<template>
    <chart-card :chart-data="pieChart.data" chart-type="Pie" style="width:80%">
    <template slot="header">
        <h4 class="card-title">Resource Breakdown</h4>
    </template>
    <template slot="footer">
        <div class="legend" style="width: 100%">
            <table style="width: 100%">
                <tr style="width: 100%">
                    <td style="width: 33%">
                        <i class="fa fa-circle text-info"></i> Finance
                    </td>
                    <td style="width: 33%">
                        <i class="fa fa-circle text-danger"></i> IT
                    </td>
                    <td style="width: 33%">
                        <i class="fa fa-circle text-warning"></i> Business Analysts
                    </td>
                </tr>
            </table>
        </div>
    </template>
    </chart-card>
</template>

<script>
import ChartCard from 'src/components/UIComponents/Cards/ChartCard.vue'

export default {
    name: 'resource-breakdown',
    components: {
      ChartCard
    },
    data () {
        return {
            pieChart: {
                data: {
                    labels: ['28%', '40%', '32%'],
                    series: [28, 40, 32]
                }
            }
        }
    }
}
</script>

<style>

</style>
