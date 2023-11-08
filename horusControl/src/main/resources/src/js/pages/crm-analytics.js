import emailCampaignReportsChartInit from '../theme/charts/echarts/email-campaign-report';
import socialMarketingRadarChartInit from '../theme/charts/echarts/social-marketing-radar-chart';
import salesTrendsChartInit from '../theme/charts/echarts/sales-trends-chart';
import callCampaignChartInit from '../theme/charts/echarts/call-campaign-reports';

const { docReady } = window.phoenix.utils;

docReady(emailCampaignReportsChartInit);
docReady(socialMarketingRadarChartInit);
docReady(salesTrendsChartInit);
docReady(callCampaignChartInit);
