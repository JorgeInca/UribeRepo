// import AnchorJS from 'anchor-js';

const anchorJSInit = () => {
  const anchors = new window.AnchorJS({
    icon: '#'
  });
  anchors.add('[data-anchor]');
};

export default anchorJSInit;
