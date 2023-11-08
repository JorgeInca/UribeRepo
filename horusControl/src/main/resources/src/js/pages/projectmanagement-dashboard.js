import issuesDiscoveredChartInit from '../theme/charts/echarts/issues-discovered-chart';
import zeroBurnOutChartInit from '../theme/charts/echarts/zero-burnout-chart';
import zeroRoadmapChartInit from '../theme/charts/echarts/zero-rodamap-chart';

const { docReady } = window.phoenix.utils;

docReady(zeroRoadmapChartInit);
docReady(zeroBurnOutChartInit);
docReady(issuesDiscoveredChartInit);
