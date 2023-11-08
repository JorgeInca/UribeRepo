import { echartSetOption, handleTooltipPosition } from './echarts-utils';

// dayjs.extend(advancedFormat);

/* -------------------------------------------------------------------------- */
/*                             Echarts Total Sales                            */
/* -------------------------------------------------------------------------- */

const issuesDiscoveredChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const issuesDiscoveredChartEl = document.querySelector('.echart-issue-chart');

  if (issuesDiscoveredChartEl) {
    const userOptions = getData(issuesDiscoveredChartEl, 'echarts');
    const chart = window.echarts.init(issuesDiscoveredChartEl);

    const getDefaultOptions = () => ({
      color: [
        getColor('info-300'),
        getColor('warning-300'),
        getColor('danger-300'),
        getColor('success-300'),
        getColor('primary')
      ],
      tooltip: {
        trigger: 'item',
        position: (...params) => handleTooltipPosition(params)
      },
      responsive: true,
      maintainAspectRatio: false,

      series: [
        {
          name: 'Tasks assigned to me',
          type: 'pie',
          radius: ['48%', '90%'],
          startAngle: 30,
          avoidLabelOverlap: false,
          // label: {
          //   show: false,
          //   position: 'center'
          // },

          label: {
            show: false,
            position: 'center',
            formatter: '{x|{d}%} \n {y|{b}}',
            rich: {
              x: {
                fontSize: 31.25,
                fontWeight: 800,
                color: getColor('gray-700'),
                padding: [0, 0, 5, 15]
              },
              y: {
                fontSize: 12.8,
                color: getColor('gray-700'),
                fontWeight: 600
              }
            }
          },
          emphasis: {
            label: {
              show: true
            }
          },
          labelLine: {
            show: false
          },
          data: [
            { value: 78, name: 'Product design' },
            { value: 63, name: 'Development' },
            { value: 56, name: 'QA & Testing' },
            { value: 36, name: 'Customer queries' },
            { value: 24, name: 'R & D' }
          ]
        }
      ],
      grid: {
        bottom: 0,
        top: 0,
        left: 0,
        right: 0,
        containLabel: false
      }
    });

    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default issuesDiscoveredChartInit;
