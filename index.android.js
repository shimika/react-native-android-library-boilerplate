'use strict'

import { NativeModules } from 'react-native'

// name as defined via ReactContextBaseJavaModule's getName

module.exports = {
  track: function(pluginkey, userId, profile) {
    if (!pluginkey) {
      console.log('pluginkey can not be null or empty')
      return
    }
    if (!userId) {
      console.log('userId can not be null or empty')
      return
    }

    console.log(pluginkey, userId, profile)
  },
  ...NativeModules.ChannelIOSynergy
}
