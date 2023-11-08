const supportChatInit = () => {
  const supportChat = document.querySelector('.support-chat');
  const supportChatBtn = document.querySelectorAll('.btn-support-chat');
  const supportChatcontainer = document.querySelector(
    '.support-chat-container'
  );
  const { phoenixSupportChat } = window.config.config;

  if (phoenixSupportChat) {
    supportChatcontainer?.classList.add('show');
  }
  if (supportChatBtn) {
    supportChatBtn.forEach(item => {
      item.addEventListener('click', () => {
        supportChat.classList.toggle('show-chat');

        supportChatBtn[supportChatBtn.length - 1].classList.toggle(
          'btn-chat-close'
        );

        supportChatcontainer.classList.add('show');
      });
    });
  }
};
export default supportChatInit;
