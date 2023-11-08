import contactsBySourceChartInit from '../theme/charts/echarts/contacts-by-source-chart';
import contactsCreatedChartInit from '../theme/charts/echarts/contacts-created-chart';
import newUsersChartsInit from '../theme/charts/echarts/crm-new-users';
import newLeadsChartsInit from '../theme/charts/echarts/crm-new-leads';
import addClicksChartInit from '../theme/charts/echarts/crm-add-clicks';
import echartsLeadConversiontInit from '../theme/charts/echarts/crm-lead-conversion';
import echartsRevenueTargetInit from '../theme/charts/echarts/crm-revenue-target';

const { docReady } = window.phoenix.utils;

docReady(contactsBySourceChartInit);
docReady(contactsCreatedChartInit);
docReady(newUsersChartsInit);
docReady(newLeadsChartsInit);
docReady(addClicksChartInit);
docReady(echartsLeadConversiontInit);
docReady(echartsRevenueTargetInit);
