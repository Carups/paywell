#!/usr/bin/python3

with open('clientTrustStore.jks', 'rb') as f, open('PublicKeyHolder.java', 'w') as out:
	out.write('package ru.spbau.mit.hackathon.paywell.server; public class PublicKeyHolder { public final static byte[] certArr = {')
	b = f.read(1)
	while b:
		out.write('{:}, '.format(int.from_bytes(b, byteorder='little', signed=True)))
		b = f.read(1)
	
	out.write('};}');
