'use strict'

import { NativeModules } from 'react-native'

// name as defined via ReactContextBaseJavaModule's getName

module.exports = {
  track: function(pluginKey, userId, profile) {
    if (!pluginKey) {
      console.log('plugin key can not be null or empty')
      return
    }
    if (!userId) {
      console.log('userId can not be null or empty')
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
      }).catch((error) => {
        console.log(error)
      })
    })
  },
  ...NativeModules.ChannelIOSynergy
}
