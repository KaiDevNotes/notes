INSERT INTO account (id, email, password, role) VALUES
(X'1a86867193c74fa7b6a236141f0a0727', 'customer1@test.io', 'password1', 'USER');

INSERT INTO service_order (id, account_id, server_id, price_in_usd, status) VALUES
(X'1b86867193c74fa7b6a236141f0a0727', '1a868671-93c7-4fa7-b6a2-36141f0a0727', '1d868671-93c7-4fa7-b6a2-36141f0a0727', 8.3, 'NEW');

INSERT INTO service_user (id, login, password, server_id) VALUES
(X'1c86867193c74fa7b6a236141f0a0727', 'USR-36141F0A0727', 'PWD-36141F0A0727', '1d868671-93c7-4fa7-b6a2-36141f0a0727');

INSERT INTO server (id, ip, port, location, status) VALUES
(X'1d86867193c74fa7b6a236141f0a0727', '10.0.0.1', '8881', 'USA_NEW_YORK', 'ACTIVE'),
(X'2d86867193c74fa7b6a236141f0a0727', '10.0.0.2', '8882', 'NORWAY_OSLO', 'ACTIVE'),
(X'3d86867193c74fa7b6a236141f0a0727', '10.0.0.3', '8883', 'JAPAN_TOKYO', 'ACTIVE'),
(X'4d86867193c74fa7b6a236141f0a0727', '10.0.0.4', '8884', 'USA_NEW_YORK', 'INACTIVE');

INSERT INTO subscription_plan (id, name, price_in_usd, status) VALUES
(X'1e86867193c74fa7b6a236141f0a0727', 'Lite', 5.5, 'ACTIVE'),
(X'2e86867193c74fa7b6a236141f0a0727', 'Standard', 8.3, 'ACTIVE'),
(X'3e86867193c74fa7b6a236141f0a0727', 'Premium', 12.7, 'ACTIVE'),
(X'4e86867193c74fa7b6a236141f0a0727', 'Inactive', 0.6, 'INACTIVE');

