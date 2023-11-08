import { echartSetOption } from '../echarts-utils';

const doughnutRoundedChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector(
    '.echart-doughnut-rounded-chart-example'
  );

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      legend: {
        orient: 'vertical',
        left: 'left',
        textStyle: {
          color: getColor('gray-600')
        }
      },
      series: [
        {
          type: 'pie',
          radius: ['40%', '70%'],
          center: window.innerWidth < 530 ? ['65%', '55%'] : ['50%', '55%'],
          avoidLabelOverlap: false,
          itemStyle: {
            borderRadius: 10,
            borderColor: getColor('gray-100'),
            borderWidth: 2
          },
          label: {
            show: false,
            position: 'center'
          },
          labelLine: {
            show: false
          },
          data: [
            {
              value: 1200,
              name: 'Starter',
              itemStyle: {
                color: getColor('primary')
              }
            },
            {
              value: 1000,
              name: 'Basic',
              itemStyle: {
                color: getColor('danger')
              }
            },
            {
              value: 800,
              name: 'Optimal',
              itemStyle: {
                color: getColor('info')
              }
            },
            {
              value: 600,
              name: 'Business',
              itemStyle: {
                color: getColor('success')
              }
            },
            {
              value: 400,
              name: 'Premium',
              itemStyle: {
                color: getColor('warning')
              }
            }
          ]
        }
      ],
      tooltip: {
        trigger: 'item',
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        transitionDuration: 0,
        axisPointer: {
          type: 'none'
        }
      }
    });

    const responsiveOptions = {
      xs: {
        series: [
          {
            center: ['65%', '55%']
          }
        ]
      },
      sm: {
        series: [
          {
            center: ['50%', '55%']
          }
        ]
      }
    };

    echartSetOption(chart, userOptions, getDefaultOptions, responsiveOptions);
  }
};

export default doughnutRoundedChartInit;
