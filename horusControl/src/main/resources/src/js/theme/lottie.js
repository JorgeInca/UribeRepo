const lottieInit = () => {
  const { getData } = window.phoenix.utils;
  const lotties = document.querySelectorAll('.lottie');
  if (lotties.length) {
    lotties.forEach(item => {
      const options = getData(item, 'options');
      window.bodymovin.loadAnimation({
        container: item,
        path: '../img/animated-icons/warning-light.json',
        renderer: 'svg',
        loop: true,
        autoplay: true,
        name: 'Hello World',
        ...options
      });
    });
  }
};

export default lottieInit;
