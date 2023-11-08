/* eslint-disable no-new */

/* -------------------------------------------------------------------------- */
/*                            ChartJs Initialization                          */
/* -------------------------------------------------------------------------- */

export const chartJsInit = (chartEl, config) => {
  if (!chartEl) return;

  const ctx = chartEl.getContext('2d');
  if (ctx) {
    let chart = new window.Chart(ctx, config());

    const themeController = document.body;
    themeController.addEventListener(
      'clickControl',
      ({ detail: { control } }) => {
        if (control === 'phoenixTheme') {
          chart.destroy();
          chart = new window.Chart(ctx, config());
        }
        return null;
      }
    );
  }
};
