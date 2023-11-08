import { echartSetOption } from '../echarts-utils';

const barGradientChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-bar-gradient-chart-example');

  const tooltipFormatter = params => {
    return `<div> 
        <h6 class="fs--1 text-700 mb-0">
        <span class="fas fa-circle me-1 text-primary"></span> ${params[0].name} : ${params[0].value} 
         </h6>
      </div> `;
  };

  const dataAxis = [
    'A',
    'B',
    'C',
    'D',
    'E',
    'F',
    'G',
    'H',
    'I',
    'J',
    'K',
    'L',
    'M',
    'N',
    'O',
    'P',
    'Q',
    'R',
    'S',
    'T'
  ];
  const data = [
    320, 190, 200, 210, 256, 451, 111, 150, 442, 321, 100, 451, 201, 232, 140,
    124, 444, 333, 222, 111
  ];

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      tooltip: {
        trigger: 'axis',
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        transitionDuration: 0,
        axisPointer: {
          type: 'none'
        },
        formatter: params => tooltipFormatter(params)
      },
      title: {
        text: 'Gradient and Clickable bar chart',
        textStyle: {
          color: getColor('gray-600')
        },
        left: 'center'
      },
      xAxis: {
        data: dataAxis,
        axisLabel: {
          inside: true,
          textStyle: {
            color: '#fff'
          }
        },
        axisTick: {
          show: false
        },
        axisLine: {
          show: false
        },
        z: 10
      },
      yAxis: {
        axisLine: {
          show: false
        },
        axisTick: {
          show: false
        },
        axisLabel: {
          textStyle: {
            color: getColor('gray-600')
          }
        },
        splitLine: {
          show: true,
          lineStyle: {
            color: getColor('gray-300')
          }
        }
      },
      dataZoom: [
        {
          type: 'inside'
        }
      ],
      series: [
        {
          type: 'bar',
          name: 'Total',
          showBackground: true,
          itemStyle: {
            color: new window.echarts.graphic.LinearGradient(0, 0, 0, 1, [
              { offset: 0, color: getColor('info') },
              { offset: 0.5, color: getColor('primary') },
              { offset: 1, color: getColor('primary') }
            ]),
            barBorderRadius: [3, 3, 0, 0]
          },
          emphasis: {
            itemStyle: {
              color: new window.echarts.graphic.LinearGradient(0, 0, 0, 1, [
                { offset: 0, color: getColor('primary') },
                { offset: 0.7, color: getColor('primary') },
                { offset: 1, color: getColor('info') }
              ])
            }
          },
          data
        }
      ],
      grid: {
        right: 5,
        left: 5,
        bottom: 5,
        top: '10%',
        containLabel: true
      }
    });
    echartSetOption(chart, userOptions, getDefaultOptions);

    const zoomSize = 6;
    chart.on('click', params => {
      chart.dispatchAction({
        type: 'dataZoom',
        startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
        endValue:
          dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
      });
    });
  }
};

export default barGradientChartInit;
