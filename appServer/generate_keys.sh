#!/bin/bash

rm serverKeyStore.jks

keytool -genkey -keyalg RSA -storepass changeit -keystore serverKeyStore.jks

keytool -exportcert  -keystore serverKeyStore.jks -storepass changeit -file publickey.cert

keytool -importcert  -file publickey.cert -keystore clientTrustStore.jks -storepass changeit

rm publickey.cert

./hardcodeCertificateToByteArray.py

rm clientTrustStore.jks
