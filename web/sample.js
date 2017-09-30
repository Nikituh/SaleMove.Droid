'use strict';
const button = document.querySelector('.talk-to-human');
button.addEventListener('click', () => {
  sm.getApi({version: 'v1'}).then((salemove) => {
    const request = salemove.requestEngagement('video');
    button.classList.add('loading');
    request.engagementPromise.then((engagement) => {
      button.classList.remove('loading');
      button.classList.add('hidden');
    });
  });
});
