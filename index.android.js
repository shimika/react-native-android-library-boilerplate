'use strict'

import { NativeModules } from 'react-native'

// name as defined via ReactContextBaseJavaModule's getName

module.exports = {
  track: function(pluginKey, userId, profile) {
    return new Promise(function(resolve, reject) {
      if (!pluginKey) {
        reject(new Error('plugin key can not be null or empty'))
        return
      }
      if (!userId) {
        reject(new Error('userId can not be null or empty'))
        return
      }

      NativeModules.ChannelIOSynergy.getDeviceId((wId, adId) => {
        var xhr = new XMLHttpRequest()
        xhr.open('POST', `https://api.channel.io/app/plugins/${pluginKey}/boot/v2`, true)
        xhr.setRequestHeader('Accept', 'application/json')
        xhr.setRequestHeader('Content-type', 'application/json')
        xhr.onload = function () {
            // do something to response
            if (xhr.status === 200) {
              resolve()
            } else {
              reject(xhr.responseText && JSON.parse(xhr.responseText))
            }
        };
        xhr.send(JSON.stringify({
          userId,
          profile,
          sysProfile: {
            adId,
            '$wId': wId,
          }
        }))
      })
    })
  }
}
