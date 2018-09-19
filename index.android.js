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

      NativeModules.ChannelIOSynergy.getDeviceId().then(deviceId => {
        fetch(`https://api.channel.io/app/plugins/${pluginKey}/boot/v2`, {
          method: 'POST',
          headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            userId,
            profile,
            sysProfile: {
              '$wId': deviceId
            }
          })
        })
        .then(res => {
          resolve()
        })
        .catch((error) => {
          reject(error)
        })
      })
    })
  }
}
