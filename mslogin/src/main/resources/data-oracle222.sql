INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('web-client', '{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'READ,WRITE, TRUST',
	'authorization_code,password,refresh_token,implicit', null, 'ROLE_CLIENT, ROLE_TRUSTED_CLIENT', 60*60*1, 24*60*60*1, null, 'true');



INSERT INTO adminuser (username, password, firstname,lastname) VALUES ('eng.motahari@gmail.com', '{bcrypt}$2a$10$eMFS.AIypqHhOcyVsypqj.o3kwRCnwru0N5ddVDUM/7ECXgKGBI5O', 'mostafa','motaharinia');
