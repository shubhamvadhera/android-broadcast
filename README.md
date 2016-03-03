# Android - Broadcast Receiver
## Learning Objective - Broadcast Receivers

This consists of 2 apps BcSender and BcReceiver.

BcSender takes some dollar amount and target currency as input. It then sends a broadcast containing the amount and currency.

BcReceiver intercepts this broadcast, takes the amount and target currency, converts it, and sends a reply broadcast containing the converted amount.

Upon successful conversion, the main activity (BcSender) displays note of conversion.
