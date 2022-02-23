apt update<br>
apt install -y --no-install-recommends docker.io<br>
echo 'nameserver 2a03:7900:2:0:31:3:104:161' > /etc/resolv.conf<br>
echo {\\"ipv6\\":true, \\"fixed-cidr-v6\\":\\"fd00::/64\\"} > /etc/docker/daemon.json<br>
ip6tables -t nat -I POSTROUTING -j MASQUERADE<br>
systemctl restart docker<br>
mkdir -p /etc/letsencrypt/live/chaowenguo.eu.org<br>
scp -o StrictHostKeyChecking=no -i key -r root@[2a02:0180:0006:0001:0000:0000:0000:3142]:/etc/letsencrypt/live/chaowenguo.eu.org /etc/letsencrypt/live
